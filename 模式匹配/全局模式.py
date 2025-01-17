from transformers import BertModel, BertTokenizer
import torch
import numpy as np

# 加载预训练BERT模型和tokenizer
model_name = "bert-base-chinese"
tokenizer = BertTokenizer.from_pretrained(model_name)
model = BertModel.from_pretrained(model_name)

# 定义全局模式字段
train_labels = ["银行名称", "证件号码", "客户名称", "账号", "卡号", 
                "币种", "交易日期","交易时间", "借贷标志", "金额数量",
                "余额","对方账号","对方户名","对方银行"]

# 初始化一个空的列表，用于存储输出向量
output_vectors = []
#input = input("请输入字段：")
#print("input内容： "+input)

# 循环遍历字符数组，对每个字符进行编码并输出向量
for label in train_labels:
    # 使用tokenizer对字符进行编码
    input_ids = tokenizer.encode(label, add_special_tokens=True)
    # 将编码转换成PyTorch tensor
    input_ids_tensor = torch.tensor([input_ids])
    # 使用BERT模型进行推理
    with torch.no_grad():
        outputs = model(input_ids_tensor)
    
    # 获取输出向量
    last_hidden_state = outputs.last_hidden_state
    output_vector = last_hidden_state[0][-1].numpy()  # 获取最后一个 token 的输出向量并转换为 NumPy 数组

    # 将输出向量添加到列表中
    output_vectors.append(output_vector)

# 将输出向量列表转换为 NumPy 数组
output_vectors = np.array(output_vectors)

# 输出保存的输出向量
print("保存的输出向量：")
print(output_vectors)

# 保存输出向量到文件
np.save("output_vectors.npy", output_vectors)


print("-------------------我是分隔符-------------------")
print("-------------------我是分隔符-------------------")