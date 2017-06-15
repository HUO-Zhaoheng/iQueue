#ifndef Office
#define Office

#include <string>
#include "QueueInfo.h"
#include "OfficeInfo.h"
#include "Doctor.h"

class Office {
public:

	string name;	//诊室名字
	//string description;	//诊室信息
	QueueInfo Queue_Info;	//诊室队列信息
	DoctorInfo DoctorInfo;	//医生信息
	Office(string);			//创建诊室
};

#endif
