#ifndef _DOCTORINFO_
#define _DOCTORINFO_
#include "PatientInfo.h"
#include <string>
#include <queue>

using std::string;
using std::queue;

class DoctorInfo {
private:
	string Name;  // 医生姓名
	string Office;  // 医生所在科室
	string EmpID;  // 医生工号
	string Introduction;  // 医生简介
	string StartTime;  // 医生开始工作时间
	string EndTime;  // 医生结束工作时间
	queue<PatientInfo*> Waiting;  // 医生下的候诊队列
	PatientInfo* Visiting;  // 医生下的就诊队列
	queue<PatientInfo*> Done;  // 医生下的完诊队列

public:
	DoctorInfo();

	DoctorInfo(string Name_, string Office_, string EmpID_, string Introduction_, string Time_, 
		string EndTime, queue<PatientInfo*> p);

	/* 根据ID匹配到数据库中相应医生，获取医生信息，保存在私有成员中 */
	DoctorInfo(string EmpID, 数据库接口);

	/* 添加医生信息进入数据库，成功则返回true */
	bool Add_DoctorInfo(Json, 数据库接口);

	/* 修改医生信息，成功则返回true */
	bool Set_DoctorInfo(Json, 数据库接口);

	/* 根据ID匹配到数据库中相应医生，删除医生信息，成功则返回true */
	bool Delete_DoctorInfo(string ID, 数据库接口);

	/* 添加病人进入候诊队列，成功则返回true */
	bool Push_Waiting(PatientInfo* p);

	/* 添加病人进入就诊队列，去掉前一个病人，成功则返回true */
	bool PushandPop_Visiting(PatientInfo* p);

	/* 添加病人进入完诊队列，成功则返回true */
	bool Push_Done(PatientInfo* p);

	/* Pop掉候诊队列中第一个，成功则返回true */
	bool Pop_Waiting();

	/* Pop掉完诊队列，成功则返回true */
	bool Pop_Waiting();

	/* 返回候诊队列 */
	queue<PatientInfo*> Get_Waiting();

	/* 返回就诊队列 */
	PatientInfo* Get_Visiting();

	/* 返回完诊队列 */
	queue<PatientInfo*> Get_Done();

	/* 返回医生信息 */
	DoctorInfo Get_DoctorInfo();
}

#endif