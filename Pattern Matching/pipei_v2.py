from transformers import BertModel, BertTokenizer
import torch
import numpy as np
import json
from flask import Flask, jsonify, request

app = Flask(__name__)

# 定义字符数组
train_labels = ["银行名称", "证件号码", "客户名称", "账号", "卡号", "币种", "交易日期", "交易时间", "借贷标志", "金额数量", "余额","对方账号","对方户名","对方银行"]

# 计算余弦距离
def cos_sim(a,b):
    a_norm=np.linalg.norm(a)
    b_norm=np.linalg.norm(b)
    cos=np.dot(a,b)/(a_norm*b_norm+ 1e-9)#添加非零小数避免除零错误
    return cos
@app.route('/get_result', methods=['POST'])
def getresult():
    input_data = request.json
    input=""
    if input_data is not None:
        input = input_data.get('input_value')
    else:
        print("数据为空")

    # 加载预训练的BERT模型和tokenizer
    model_name = "bert-base-chinese"
    tokenizer = BertTokenizer.from_pretrained(model_name)
    model = BertModel.from_pretrained(model_name)

    # 获得目标匹配字段的编码向量
    output_vectors = []
    output_vectors = np.load("output_vectors.npy")

    # 使用tokenizer对输入字符进行编码，转换成PyTorch tensor
    input_code = tokenizer.encode(input, add_special_tokens=True)
    input_code_tensor=torch.tensor([input_code])
    # 使用bert模型推理
    with torch.no_grad():
        vector=model(input_code_tensor)
    # 获得字段转化后的向量
    last_hidden_state = vector.last_hidden_state
    fianl_vector=last_hidden_state[0][-1].numpy() # 获取最后一个token的输出向量并转换为NumPy数组

    #计算余弦距离
    all_cos_sim=[]#用来存储余弦距离

    for j in range(len(output_vectors)):
        cosSim_result=cos_sim(fianl_vector,output_vectors[j])#计算余弦距离
        all_cos_sim.append(cosSim_result)#添加到列表

    index_result=all_cos_sim.index(max(all_cos_sim))#获得匹配结果下标
   
    return jsonify({"result":train_labels[index_result]})  #返回匹配结果的json串
app.run(host='127.0.0.1', port=8802, debug=True)

