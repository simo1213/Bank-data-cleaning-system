from transformers import BertModel, BertTokenizer
import torch
import numpy as np

# 定义字符数组
train_labels = ["银行名称", "证件号码", "客户名称", "账号", "卡号", "币种", "交易日期", "交易时间", "借贷标志", "金额数量", "余额","对方账号","对方户名","对方银行"]

# 定义测试数据集
test_A = [ "银行", "身份证号码", "客户姓名", "账户号码", "银行卡号码", "货币类型", "交易日期", 
          "交易时间", "借贷标志", "金额数量", "账户余额","对方账号","对方户名","对方银行"]

test_B = ["银行机构", "身份识别号", "用户名称", "账户编号", "银行卡编号", "币种类别", "交易日期",
           "交易时刻", "借贷方向", "交易金额", "账户结余","交易方账号","交易方户名","交易方银行"]

test_C = [ "银行名", "身份证号", "姓名", "账户号码", "银行卡号", "货币类型", "交易日期", "交易时间",
           "借贷", "交易数量", "账户余额","对方账号","对方姓名","对方银行"]
test_D = ['银行', '身份证编码', '客户', '账号ID', '银行卡编码', '币别', '日期时间', '交易时刻', '借贷标记', '数额', '余额', '对方账号信息', '对方姓名', '对方银行全称']
test_E = ['银行名', '证件编号', '客户姓名', '账户号', '银行卡号', '货币种类', '交易日期时间', '交易具体时间', '借贷方向', '金额', '剩余额度', '对方账号信息', '对方账户名', '对方银行名称']
test_F = ['银行名', '身份证号', '客户名', '帐户', '银行卡编码', '货币种类', '日期时间', '交易具体时间', '借贷类型', '交易金额', '余额', '对方银行账号', '对方客户名', '对方银行全称']
test_G= ['银行单位', '证件号', '客户全名', '账号ID', '银行卡号', '货币单位', '日期', '时刻', '借贷方向', '金额', '余额', '对方帐户', '对方用户', '对方金融机构']


test_3 = ["客户姓名", "用户名称", "用户姓名", "客户名", "用户名", "姓名", "客户", "用户", "名字", "顾客名称"]
test_4 = ["对方银行"]
# 计算余弦距离
def cos_sim(a,b):
    a_norm=np.linalg.norm(a)
    b_norm=np.linalg.norm(b)
    cos=np.dot(a,b)/(a_norm*b_norm)
    return cos

# 加载预训练的 BERT 模型和 tokenizer
model_name = "bert-base-chinese"
tokenizer = BertTokenizer.from_pretrained(model_name)
model = BertModel.from_pretrained(model_name)

# 获得存储的编码向量
output_vectors = []
output_vectors = np.load("output_vectors.npy")

for i in range(len(test_F)):
    # 获得列表输入
    #input = input("请输入字段：")
    input = test_F[i]
    # 使用 tokenizer 对输入字符进行编码，转换成PyTorch tensor
    input_code = tokenizer.encode(input, add_special_tokens=True)
    input_code_tensor=torch.tensor([input_code])
    # 使用bert模型推理
    with torch.no_grad():
        vector=model(input_code_tensor)
    # 获得字段转化后的向量
    last_hidden_state = vector.last_hidden_state
    fianl_vector=last_hidden_state[0][-1].numpy() # 获取最后一个 token 的输出向量并转换为 NumPy 数组

    #计算余弦距离
    all_cos_sim=[]#用来存储余弦距离
    

    for j in range(len(output_vectors)):
        cosSim_result=cos_sim(fianl_vector,output_vectors[j])#计算余弦距离
        all_cos_sim.append(cosSim_result)#添加到列表
        

    index_result=all_cos_sim.index(max(all_cos_sim))
   
    print("测试字段：",end="")
    print(test_F[i],end="")
    print("    匹配结果：",end="")
    print(train_labels[index_result],end="")  
    print("     相似度：",end="")
    print(max(all_cos_sim))  
    