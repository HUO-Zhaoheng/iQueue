using System.Windows;
using System.Windows.Data;
using BLL.Network;
using Newtonsoft.Json.Linq;
using Newtonsoft.Json;
using System.Collections.ObjectModel;
using System;
using System.Collections.Generic;

namespace iQueue
{
    /// <summary>
    /// patient_watch.xaml 的交互逻辑
    /// </summary>
    public partial class patient_watch : Window
    {

        CollectionViewSource first_view = new CollectionViewSource();
        CollectionViewSource second_view = new CollectionViewSource();
        CollectionViewSource trigae_view = new CollectionViewSource();
        CollectionViewSource waiting_view = new CollectionViewSource();
        CollectionViewSource finish_view = new CollectionViewSource();
        CollectionViewSource doctor_view = new CollectionViewSource();
        CollectionViewSource patient_view = new CollectionViewSource();
        //用来保存所有的病人信息
        ObservableCollection<PatientInfo> patients = new ObservableCollection<PatientInfo>();
        ObservableCollection<First_Second_Triage_Queue_row> first = new ObservableCollection<First_Second_Triage_Queue_row>();
        ObservableCollection<First_Second_Triage_Queue_row> second = new ObservableCollection<First_Second_Triage_Queue_row>();
        ObservableCollection<First_Second_Triage_Queue_row> triage = new ObservableCollection<First_Second_Triage_Queue_row>();
        ObservableCollection<Waiting_Finish_Queue_row> waiting = new ObservableCollection<Waiting_Finish_Queue_row>();
        ObservableCollection<Waiting_Finish_Queue_row> finish = new ObservableCollection<Waiting_Finish_Queue_row>();
        ObservableCollection<Doctor_List_row> doctors = new ObservableCollection<Doctor_List_row>();
        public patient_watch()
        {
            //初始化的时候获取所有病人信息
            InitializeComponent();
            InitInfo();
            //InitClinic();
            Init_Patient_Info();
            First_Second_Triage_Queue_row p = new First_Second_Triage_Queue_row();
        }
        void view_Filter(object sender, FilterEventArgs e)
        {
            int index = patients.IndexOf((PatientInfo)e.Item);
        }

        private void Add_Click(object sender, RoutedEventArgs e)
        {
            add_patient ap = new add_patient();
            ap.Show();
        }
        private void Register(object sender, RoutedEventArgs e)
        {
            //挂号
        }
        private void CheckIn(object sender, RoutedEventArgs e)
        {
            //报道
        }
        private void Next(object sender, RoutedEventArgs e)
        {
            if (First_queue.SelectedIndex == -1)
                MessageBox.Show("请选中一个病人");
            else
            {
                try
                {
                    NetworkWorker nw = new NetworkWorker();
                    string result = nw.Send_action(first[First_queue.SelectedIndex].Name);
                    //下一个
                    MessageBox.Show(first[First_queue.SelectedIndex].Name);
                    triage.Add(first[First_queue.SelectedIndex]);
                    trigae_view.Source = triage;
                    Triage_queue.DataContext = trigae_view;
                    first.Remove(first[First_queue.SelectedIndex]);
                    first_view.Source = first;
                    First_queue.DataContext = first_view;
                    //Init();
                }
                catch
                {
                    MessageBox.Show("服务器错误");
                }

            }

        }


        private void Add_office(object sender, RoutedEventArgs e)
        {
            add_office ao = new add_office();
            ao.Show();
        }
        private void InitInfo()
        {
            INITINFO initinfo_ = new INITINFO();
            try
            {
                NetworkWorker nw = new NetworkWorker();
                string result = nw.initinfo(initinfo_);
                JObject json = (JObject)JsonConvert.DeserializeObject(result);
                //截取病人信息


                try
                {
                    int position = 0;
                    //获取首诊队列信息
                    //MessageBox.Show(json["firstQueue"].ToString());
                    JArray array = (JArray)(json["firstQueue"]);

                    foreach (var jObject in array)
                    {
                        first.Add(new First_Second_Triage_Queue_row()
                        {
                            Position = (position++).ToString(),
                            Name = jObject["name"].ToObject<string>(),
                            Time = jObject["arriveTime"].ToObject<string>()
                        });
                    }
                    //sort_queue_by_time(ref first);
                    first_view.Source = first;
                    First_queue.DataContext = first_view;
                }
                catch
                {
                    MessageBox.Show("无法解析首诊队列数据");
                }
                try
                {
                    int position = 0;
                    //获取二诊队列信息
                    //MessageBox.Show(json["secondQueue"].ToString());
                    JArray array = (JArray)(json["secondQueue"]);
                    foreach (var jObject in array)
                    {
                        second.Add(new First_Second_Triage_Queue_row()
                        {
                            Position = (position++).ToString(),
                            Name = jObject["name"].ToObject<string>(),
                            Time = jObject["arriveTime"].ToObject<string>()
                        });
                    }
                    second_view.Source = second;
                    Second_queue.DataContext = second_view;
                }
                catch
                {
                    MessageBox.Show("无法解析二诊队列数据");
                }

                try
                {
                    int position = 0;
                    //获取转诊队列信息
                    //MessageBox.Show(json["triageQueue"].ToString());
                    JArray array = (JArray)(json["triageQueue"]);
                    foreach (var jObject in array)
                    {
                        triage.Add(new First_Second_Triage_Queue_row()
                        {
                            Position = (position++).ToString(),
                            Name = jObject["name"].ToObject<string>(),
                            Time = jObject["arriveTime"].ToObject<string>()
                        });
                    }
                    trigae_view.Source = triage;
                    Triage_queue.DataContext = trigae_view;
                }
                catch
                {
                    MessageBox.Show("无法解析转诊队列数据");
                }

                /*
                try
                {
                    //获取医生队列信息
                    MessageBox.Show(json["doctor_queue"].ToString());
                    JArray array = (JArray)(json["doctor_queue"]);
                    foreach (var jObject in array)
                    {
                        doctors.Add(new Doctor_List_row()
                        {
                            Name = jObject["name"].ToObject<string>(),
                            Office = jObject["office"].ToObject<string>(),

                        });
                    }
                    doctor_view.Source = doctors;
                    DoctorView.DataContext = doctor_view;
                }
                catch
                {
                    MessageBox.Show("无法解析医生数据");
                }
                */
            }
            catch
            {
                MessageBox.Show("服务器错误");
            }
        }
        private void InitClinic()
        {
            INITCLINIC initclinic_ = new INITCLINIC();
            try
            {
                NetworkWorker nw = new NetworkWorker();
                string result = nw.initclinic(initclinic_);
                JObject json = (JObject)JsonConvert.DeserializeObject(result);
                //截取病人信息

                try
                {
                    int position = 0;
                    //获取候诊队列信息
                    MessageBox.Show(json["waitingQueue"].ToString());
                    JArray array = (JArray)(json["waitingQueue"]);
                    foreach (var jObject in array)
                    {
                        waiting.Add(new Waiting_Finish_Queue_row()
                        {
                            Position = (position++).ToString(),
                            Name = jObject["name"].ToObject<string>(),
                            Time = jObject["time"].ToObject<string>(),

                        });
                    }
                    waiting_view.Source = waiting;
                    Waiting_queue.DataContext = waiting_view;
                }
                catch
                {
                    MessageBox.Show("无法解析侯诊队列数据");
                }
                try
                {
                    int position = 0;
                    //获取完诊队列信息
                    MessageBox.Show(json["finishQueue"].ToString());
                    JArray array = (JArray)(json["finishQueue"]);
                    foreach (var jObject in array)
                    {
                        finish.Add(new Waiting_Finish_Queue_row()
                        {
                            Position = (position++).ToString(),
                            Name = jObject["name"].ToObject<string>(),
                            Time = jObject["time"].ToObject<string>(),

                        });
                    }
                    finish_view.Source = finish;
                    Finish_queue.DataContext = finish_view;
                }
                catch
                {
                    MessageBox.Show("无法解析完诊队列数据");
                }
                /*
                try
                {
                    //获取医生队列信息
                    MessageBox.Show(json["doctor"].ToString());
                    JArray array = (JArray)(json["doctor"]);
                    foreach (var jObject in array)
                    {

                        Doctor_Name.Text = jObject["name"].ToObject<string>();
                        Doctor_Profile.Text = jObject["profile"].ToObject<string>();
                        Doctor_StartTime.Text = jObject["startTime"].ToObject<string>();
                        Doctor_EndTime.Text = jObject["endTime"].ToObject<string>();
                    }
                    doctor_view.Source = doctors;
                    DoctorView.DataContext = doctor_view;
                }
                catch
                {
                    MessageBox.Show("无法解析诊室医生信息");
                }
                */
            }
            catch
            {
                MessageBox.Show("服务器错误");
            }
        }
        private void Init_Patient_Info()
        {

            INITPATIENTINFO initPatientInfo_ = new INITPATIENTINFO();
            NetworkWorker nw = new NetworkWorker();
            string result = nw.initPatientinfo(initPatientInfo_);
            JObject json = (JObject)JsonConvert.DeserializeObject(result);
            try
            {
                JArray patientInfo = json["patientInfo"].ToObject<JArray>();
                //MessageBox.Show(patientInfo.ToString());
                int i = 0;
                PatientInfo pi = new PatientInfo();
                foreach (var JObject in json["patientInfo"])
                {
                    if (i%2==0)
                    {
                        pi.patientID = JObject["pId"].ToObject<string>();
                        pi.name = JObject["name"].ToObject<string>();
                        pi.cardNum = JObject["cardNumber"].ToObject<string>();
                        pi.sex = JObject["sex"].ToObject<string>();
                        pi.age = JObject["age"].ToObject<string>();
                        pi.registration_time = JObject["registerTime"].ToObject<string>();
                        pi.visting_time = JObject["arriveTime"].ToObject<string>();
                    }
                    else
                    {
                        pi.height = JObject["height"].ToObject<string>();
                        pi.weight = JObject["weight"].ToObject<string>();
                        pi.temperature = JObject["temperature"].ToObject<string>();
                        pi.respiration = JObject["respiration"].ToObject<string>();
                        pi.pulse = JObject["pulse"].ToObject<string>();
                        pi.blood_pressure = JObject["bloodPressure"].ToObject<string>();
                        pi.disease_description = JObject["description"].ToObject<string>();
                        pi.blood_sugar = JObject["bloodSugar"].ToObject<string>();
                        patients.Add(pi);
                    }
                    i++;
                }
                patient_view.Source = patients;
                PatientView.DataContext = patient_view;
            }
            catch
            {
                MessageBox.Show("无法解析病人数据");
            }
        }
        void sort_queue_by_time(ref ObservableCollection<First_Second_Triage_Queue_row> queue)
        {
            int[] selected = new int[queue.Count];
            ObservableCollection<First_Second_Triage_Queue_row> temp = new ObservableCollection<First_Second_Triage_Queue_row>();
            List<long> list = new List<long>();
            for (int i = 0; i < queue.Count; i++)
            {
                long time_JAVA_Long = long.Parse(queue[i].Time);
                list.Add(time_JAVA_Long);
            }
            list.Sort();

            for (int i = 0; i < queue.Count; i++)
                for (int j = 0; j < queue.Count; j++)
                    if (list[i] == long.Parse(queue[j].Time))
                    {
                        temp.Add(queue[j]);
                    }
            queue = temp;
        }
        void sort_queue_by_time(ref ObservableCollection<Waiting_Finish_Queue_row> queue)
        {
            int[] selected = new int[queue.Count];
            ObservableCollection<Waiting_Finish_Queue_row> temp = new ObservableCollection<Waiting_Finish_Queue_row>();
            List<DateTime> list = new List<DateTime>();

            for (int i = 0; i < queue.Count; i++)
            {
                long time_JAVA_Long = long.Parse(queue[i].Time);//java长整型日期，毫秒为单位
                DateTime dt_1970 = new DateTime(1970, 1, 1);
                long tricks_1970 = dt_1970.Ticks;//1970年1月1日刻度
                long time_tricks = tricks_1970 + time_JAVA_Long * 10000;//日志日期刻度
                DateTime dt = new DateTime(time_tricks);//转化为DateTime
                list.Add(dt);
            }
            list.Sort();

            for (int i = 0; i < queue.Count; i++)
                for (int j = 0; j < queue.Count; j++)
                    if (list[i].ToString() == queue[j].Time)
                    {
                        temp[i] = queue[j];
                    }
            queue = temp;
        }

        private void add_patient_butt_Click(object sender, RoutedEventArgs e)
        {
            add_patient ap = new add_patient();
            ap.Show();
        }

        private void OnListViewItemDoubleClick(object sender, RoutedEventArgs e)
        {


        }
    }
}
