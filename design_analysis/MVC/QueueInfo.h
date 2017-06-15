#ifndef QueueInfo
#define QueueInfo

#include <queue>
#include <string>
#include "PatientInfo.h"
#include "QueueInfo.cpp"

struct Queue
{
	queue<PatientInfo*> First_diagnosis; //首诊队列
	queue<PatientInfo*> Twice_diagnosis;	//二诊队列
	queue<PatientInfo*> Triage;	//分诊队列

	/*以下三个队列与医生相关 在Doctor.h中定义*/
	//queue<PatientInfo*> Waiting; //等待队列
	//queue<PatientInfo*> Visiting;	//就诊队列
	//queue<PatientInfo*> Done;	//完诊队列
};

class QueueInfo {
private:
	Queue AllQueue;	//所有队列信息
public:
	QueueInfo();
	QueueInfo(Queue q);	//复制队列信息
	bool Add_QueueInfo(int, PatientInfo*, 数据库);	//向某队列添加病人
	bool Set_QueueInfo(int, PatientInfo*, 数据库);	//
	bool Delete_QueueInfo(int, PatientInfo*, 数据库);	//删除某队列某病人
	bool Pop_QueueInfo(int, 数据库);	//pop队首
	Queue Get_QueueInfo();	//获得队列信息
};

#endif // !QueueInfo

