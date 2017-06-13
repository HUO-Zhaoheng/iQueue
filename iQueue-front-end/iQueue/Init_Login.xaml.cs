using System.Windows;
using BLL.Network;
using Newtonsoft.Json.Linq;
using Newtonsoft.Json;

namespace iQueue
{
    /// <summary>
    /// MainWindow.xaml 的交互逻辑
    /// </summary>

    public partial class Init_Login : Window
    {
        public static string officeId;
        public Init_Login()
        {
            InitializeComponent();

        }
        public void Send_Click(object sender, RoutedEventArgs e)
        {
            try
            {
                User henry = new User();
                if ((henry.username = username.Text) == "") { MessageBox.Show("请输入账号"); return; }
                if ((henry.password = password.Text) == "") { MessageBox.Show("请输入密码"); return; }
                NetworkWorker nw = new NetworkWorker();
                string result = nw.Send_action(henry);
                JObject json = (JObject)JsonConvert.DeserializeObject(result);
                if (json["status"].ToString() == "success")
                {
                    MessageBox.Show("登陆成功");
                    try
                    {
                        officeId= json["oid"].ToString();
                        MessageBox.Show("officeId is "+ officeId);
                        patient_watch pw = new patient_watch();
                        pw.Show();
                    }
                    catch
                    {
                        MessageBox.Show("无法初始化");
                    }

                    this.Close();
                }
                else if (json["status"].ToString() == "name_or_password_error")
                {
                    MessageBox.Show("用户名或密码错误");
                }
            }
            catch
            {
                MessageBox.Show("无法连接服务器");
            }
        }
    }
}