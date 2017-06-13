using System;
using System.Windows;
using BLL.Network;
using Newtonsoft.Json.Linq;
using Newtonsoft.Json;

namespace iQueue
{
    /// <summary>
    /// add_patient.xaml 的交互逻辑
    /// </summary>
    public partial class add_patient : Window
    {
        public add_patient()
        {
            InitializeComponent();
        }

        private void commit_Click(object sender, RoutedEventArgs e)
        {
            PatientInfo pi = new PatientInfo();
            pi.patientID = patientID.Text;
            pi.name = name.Text;
            pi.cardNum = cardNum.Text;
            if (male.IsChecked == true)
                pi.sex = "male";
            else pi.sex = "female";
            pi.age = age.Text;
            pi.registration_time = DateTime.Now.ToString();
            pi.visting_time = "";
            pi.height = height.Text;
            pi.weight = weight.Text;
            pi.temperature = temperature.Text;
            pi.respiration = respiration.Text;
            pi.pulse = pulse.Text;
            pi.blood_pressure = blood_pressure.Text;
            pi.disease_description = disease_description.Text;
            pi.blood_sugar = blood_sugar.Text;

            NetworkWorker nw = new NetworkWorker();
            string result = nw.Send_action(pi);
            try
            {
                JObject json = (JObject)JsonConvert.DeserializeObject(result);
                if (json["status"].ToString() == "success")
                {
                    MessageBox.Show("病人添加成功");

                }
                else if (json["status"].ToString() == "duplicate")
                {
                    MessageBox.Show("病人已存在");
                }

            }
            catch { MessageBox.Show("服务器连接失败"); }

        }

        private void resume_Click(object sender, RoutedEventArgs e)
        {
            Close();
        }
    }
}
