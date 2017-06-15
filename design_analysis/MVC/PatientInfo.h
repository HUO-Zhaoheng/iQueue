#ifndef _PATIENTINFO_
#define _PATIENTINFO_
#include <string>
#include "DoctorInfo.h"

using std::string;

enum sex {female, male};

/* 病人体征信息 */
class SignInfo {
public:
	string height;  // 身高
	string weight;  // 体重
	string temperature;  // 体温
	string respiration;  // 呼吸
	string pulse;  // 脉搏
	string blood_pressure;  // 血压
	string disease_description;  // 病症描述
	string blood_sugar;  // 血糖

	SignInfo();

	SignInfo(string height_, string weight_,string temperature_, string respiration_, 
		string pulse_, string blood_pressure_, string disease_description_, string blood_sugar_);
}

class PatientInfo {
private:
	string ID;  // 挂号序列号
	string Name;  // 病人姓名
	string CardNum // 病人就序卡列号
	sex Sex;  // 病人性别
	string Age;  // 病人年龄
	string Registration_Time; // 病人挂号时间
	string Visting_Time;  // 病人到科室分诊护士站的报道时间
	SignInfo Patient_SignInfo;  // 病人体征信息
	DoctorInfo doctor;  // 给病人看病的医生信息


public:
	PatientInfo();

	/* 根据ID匹配到数据库中相应病人，获取病人信息，保存在私有成员中 */
	PatientInfo(string ID, 数据库接口);

	/* 添加病人信息进入数据库，成功则返回true */
	bool Add_PatientInfo(Json, 数据库接口);

	/* 修改病人信息，成功则返回true */
	bool Set_PatientInfo(Json, 数据库接口);

	/* 根据ID匹配到数据库中相应病人，删除病人信息，成功则返回true */
	bool Delete_PatientInfo(string ID, 数据库接口);

	/* 返回病人信息 */
	PatientInfo Get_PatientInfo();

	/* 返回给该病人看病的医生的信息 */
	DoctorInfo Get_DoctorInfo();
}

#endif