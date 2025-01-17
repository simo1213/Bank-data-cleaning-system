import numpy as np
from sklearn.feature_extraction.text import CountVectorizer
import jieba

# 分词并向量化函数
def tokenize(text):
    print(' '.join(jieba.cut(text)))
    return ' '.join(jieba.cut(text))

# 计算余弦相似度函数
def cosine_similarity(vec1, vec2):
    dot_product = np.dot(vec1, vec2)
    norm_vec1 = np.linalg.norm(vec1)
    norm_vec2 = np.linalg.norm(vec2)
    if norm_vec1 == 0 or norm_vec2 == 0:
        return 0.0
    cos_sim = dot_product / (norm_vec1 * norm_vec2)
    return cos_sim

# 匹配函数
def match_fields(test_fields, train_labels):
    # 分词
    train_labels_tokenized = [tokenize(label) for label in train_labels]
    test_fields_tokenized = [tokenize(field) for field in test_fields]

    # 使用CountVectorizer将分词结果转化为词频向量。
    vectorizer = CountVectorizer().fit(train_labels_tokenized + test_fields_tokenized)
    train_vectors = vectorizer.transform(train_labels_tokenized).toarray()
    test_vectors = vectorizer.transform(test_fields_tokenized).toarray()

    # 进行匹配
    matches = {}
    for i, test_vector in enumerate(test_vectors):
        similarities = [cosine_similarity(test_vector, train_vector) for train_vector in train_vectors]
        best_match_index = np.argmax(similarities)
        matches[test_fields[i]] = train_labels[best_match_index]

    return matches

# 全局模式标签
train_labels = ["银行名称", "证件号码", "客户名称", "账号", "卡号", "币种", "交易日期", "交易时间", "借贷标志", "金额数量", "余额", "对方账号", "对方户名", "对方银行"]

# 测试数据
test_A = ["银行", "身份证号码", "客户姓名", "账户号码", "银行卡号码", "货币类型", "交易日期", 
          "交易时间", "借贷标志", "金额数量", "账户余额", "对方账号", "对方户名", "对方银行"]

test_B = ["银行机构", "身份识别号", "用户名称", "账户编号", "银行卡编号", "币种类别", "交易日期",
          "交易时刻", "借贷方向", "交易金额", "账户结余", "交易方账号", "交易方户名", "交易方银行"]

test_C = ["银行名", "身份证号", "姓名", "账户号码", "银行卡号", "货币类型", "交易日期", "交易时间",
          "借贷", "交易数量", "账户余额", "对方账号", "对方姓名", "对方银行"]

test_D = ['银行', '身份证编码', '客户', '账号ID', '银行卡编码', '币别', '日期时间', '交易时刻', 
          '借贷标记', '数额', '余额数量', '对方账号信息', '对方名称', '对方银行全称']

test_E = ['银行名', '证件编号', '客户姓名', '账户号', '银行卡号', '货币种类', '交易日期时间', 
          '交易具体时间', '借贷方向', '金额', '剩余额度', '对方账号信息', '对方账户名', '对方银行名称']

test_F = ['银行名', '身份证号', '客户名', '帐户', '银行卡编码', '货币种类', '日期时间', '交易具体时间',
           '借贷类型', '交易金额', '余额', '对方银行账号', '对方客户名', '对方银行全称']

test_G= ['银行单位', '证件号', '客户全名', '账号ID', '银行卡号', '货币单位', '日期', '时刻', '借贷方向',
          '金额', '余额', '对方帐户', '对方用户', '对方金融机构']

# 匹配
matches_A = match_fields(test_A, train_labels)
# matches_B = match_fields(test_B, train_labels)
# matches_C = match_fields(test_C, train_labels)
# matches_D =match_fields(test_D, train_labels)
# matches_E =match_fields(test_E, train_labels)
# matches_F=match_fields(test_F, train_labels)
# matches_G =match_fields(test_G, train_labels)

print("test_A:")
print(matches_A)
# print("\ntest_B:")
# print(matches_B)
# print("\ntest_C:")
# print(matches_C)
# print("\ntest_D:")
# print(matches_D)
# print("\ntest_E:")
# print(matches_E)
# print("\ntest_F:")
# print(matches_F)
# print("\ntest_G:")
# print(matches_G)
