namespace BLL.Network
{
    //用户
    public class User
    {
        public string username { get; set; }
        public string password { get; set; }
    }
    //病人信息
    public class PatientInfo
    {
        public string patientID { get; set; }
        public string name { get; set; }
        public string cardNum { get; set; }
        public string sex { get; set; }
        public string age { get; set; }
        public string registration_time { get; set; }
        public string visting_time { get; set; }
        public string height { get; set; }
        public string weight { get; set; }
        public string temperature { get; set; }
        public string respiration { get; set; }
        public string pulse { get; set; }
        public string blood_pressure { get; set; }
        public string disease_description { get; set; }
        public string blood_sugar { get; set; }
    }
    public class row
    {
        public string Position { get; set; }
        public string Name { get; set; }
        public string Time { get; set; }
        public string pId { get; set; }
    }
    public class Doctor_List_row
    {
        public string Name { get; set; }
        public string Office { get; set; }
    }
    //诊室信息
    public class OfficeInfo
    {
        public string officeID { get; set; }
        public string name { get; set; }
    }
    //医生信息
    public class DoctorInfo
    {
        public string doctorID { get; set; }
        public string name { get; set; }
        public string office { get; set; }
        public string empId { get; set; }
        public string introduction { get; set; }
        public string startTime { get; set; }
        public string endTime { get; set; }
    }
    //队列信息
    public class QueueInfo
    {
        public string q_officeID { get; set; }
        public string[] first_diagnosis = new string[1000];
        public string[] twice_diagnosis = new string[1000];
        public string[] triage = new string[1000];
    }
    //所有信息
    public class AllInfo
    {
        public PatientInfo[] allPatient=new PatientInfo[1000];
        public OfficeInfo[] allOffice = new OfficeInfo[1000];
        public DoctorInfo[] allDoctor = new DoctorInfo[1000];
        public QueueInfo[] allQueue = new QueueInfo[1000];
    }
    //转诊
    public class TriageOffice
    {
        public string clinicId;
        public string pId;
    }
    //转诊
    public class ToQueue
    {
        public string queueId;
        public string pId;
    }
    //Type0：初始化
    public class INITIALIZE
    {
        public string opcode  = "login";
        public User user { get; set; }
    }
    //Type1：刷新
    public class INITINFO
    {
        public string opcode  = "officeQueue";

    }
    public class INITCLINIC
    {
        public string opcode = "initClinic";

    }
    public class INITPATIENTINFO
    {
        public string opcode = "initPatientInfo";

    }
    public class INITDOCTORINFO
    {
        public string opcode = "initDoctorInfo";

    }
    public class INITQUEUEINFO
    {
        public string opcode = "officeQueue";

    }
    //Type2：患者队列变换
    public class CHANGE_PATIENT_QUEUE
    {
        public string opcode  = "2";
        public string patientID { get; set; }
        public string officeID { get; set; }
        public string current_queueID { get; set; }
        public string current_doctorID { get; set; }
        public string target_queueID { get; set; }
        public string target_doctorID { get; set; }
    }

    public class TRIAGE_OFFICE
    {
        public string opcode = "choiceDoctor";
        public string pId { get; set; }
        public string clinicId { get; set; }
    }
    //Type3： 患者诊室变换
    public class CHANGE_PATIENT_OFFICE
    {
        public string opcode  = "3";
        public string patientID { get; set; }
        public string current_officeID { get; set; }
        public string current_doctorID { get; set; }
        public string current_queueID { get; set; }
        public string target_officeID { get; set; }
    }
    //Type4：设置患者个人信息
    public class SET_PATIENT_INFO
    {
        public string opcode  = "4";
        public PatientInfo info { get; set; }
    }
    //Type5：更新诊室信息
    public class SET_OFFICE_INFO
    {
        public string opcode  = "5";
        public OfficeInfo info { get; set; }
    }
    //Type6：更新医生信息
    public class SET_DOCTOR_INFO
    {
        public string opcode  = "6";
        public DoctorInfo info { get; set; }
    }
    //Type7：添加患者信息
    public class ADD_PATIENT
    {
        public string opcode  = "add";
        public PatientInfo info { get; set; }
    }
    //Type8：添加诊室信息
    public class ADD_OFFICE
    {
        public string opcode  = "8";
        public OfficeInfo info { get; set; }
    }
    //Type9：添加医生信息
    public class ADD_DOCTOR
    {
        public string opcode  = "9";
        public DoctorInfo info { get; set; }
    }
    //Type10：医生队列变化，也就是“下一个”
    public class DOCTOR_SAY_NEXT
    {
        public string opcode  = "change";
        public string newQueueId { get; set; }
        public string pId { get; set; }
    }
    /*以下是应答*/
    //Type0：初始化
    public class INITIALIZE_ECHO
    {
        public string opcode  = "0";
        public string status;
    }
    //Type1：刷新
    public class REFRESH_ECHO
    {
        public string opcode  = "1";
        public AllInfo info { get;}
    }
    //Type2：患者队列变换
    public class CHANGE_PATIENT_QUEUE_ECHO
    {
        public string opcode  = "2";
        public string is_succeed { get; }
        public string error { get; }
    }
    //Type3： 患者诊室变换
    public class CHANGE_PATIENT_OFFICE_ECHO
    {
        public string opcode  = "3";
        public string is_succeed { get; }
        public string error { get; }
    }
    //Type4：设置患者个人信息
    public class SET_PATIENT_INFO_ECHO
    {
        public string opcode  = "4";
        public string is_succeed { get; }
        public string error { get; }
    }
    //Type5：更新诊室信息
    public class SET_OFFICE_INFO_ECHO
    {
        public string opcode  = "5";
        public string is_succeed { get; }
        public string error { get; }
    }
    //Type6：更新医生信息
    public class SET_DOCTOR_INFO_ECHO
    {
        public string opcode  = "6";
        public string is_succeed { get; }
        public string error { get; }
    }
    //Type7：添加患者信息
    public class ADD_PATIENT_ECHO
    {
        public string opcode  = "7";
        public string is_succeed { get; }
        public string error { get; }
    }
    //Type8：添加诊室信息
    public class ADD_OFFICE_ECHO
    {
        public string opcode  = "8";
        public string is_succeed { get; }
        public string error { get; }
    }
    //Type9：添加医生信息
    public class ADD_DOCTOR_ECHO
    {
        public string opcode  = "9";
        public string is_succeed { get; }
        public string error { get; }
    }
    //Type10：医生队列变化，也就是“下一个”
    public class DOCTOR_SAY_NEXT_ECHO
    {
        public string opcode  = "10";
        public string is_succeed { get; }
        public string error { get; }
    }
}
