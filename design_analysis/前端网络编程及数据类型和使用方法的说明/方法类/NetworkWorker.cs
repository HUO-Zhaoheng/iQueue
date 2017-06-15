using System.Collections.Generic;
using System.Net;
using System.Net.Sockets;
using System.Threading;
using BLL.Setting;
using Newtonsoft.Json;
using System.Text;
namespace BLL.Network
{
    public class NetworkWorker
    {
        public List<byte[]> InputMessages { get; set; } = new List<byte[]>();

        private readonly IPAddress _remoteIpAddress;
        private readonly int _remotePort;
        private readonly int _localPort;

        public NetworkWorker(ConnectionSetting setting)
        {
            _remoteIpAddress = setting.ip;
            _remotePort = setting.remotePort;
            _localPort = setting.localPort;
            
        }

        private void Send(byte[] message)
        {
            UdpClient client = new UdpClient();
            IPEndPoint ipEndPoint = new IPEndPoint(_remoteIpAddress, _remotePort);

            try
            {
                client.Send(message, message.Length, ipEndPoint);
            }
            finally
            {
                client.Close();
            }
        }




        public void Receiver()
        {

            var client = new UdpClient(_localPort);
            IPEndPoint RemoteIpEndPoint = null;

            try
            {

                while (true)
                {
                    byte[] inputRowByte = client.Receive(ref RemoteIpEndPoint);
                    //var MessageToString = Encoding.Default.GetString(inputRowByte);
                    InputMessages.Add(inputRowByte);
                }
            }
            finally
            {
                client.Close();
            }
        }
        public void Send_action(ConnectionSetting setting, object pb)
        {
            var JsonMessage = JsonConvert.SerializeObject(pb);
            byte[] rowData = Encoding.UTF8.GetBytes(JsonMessage);
            NetworkWorker net = new NetworkWorker(setting);
            net.Send(rowData);
        }
    }
}
