using System.Net;
using System.Runtime.Serialization;

namespace BLL.Setting
{
    [DataContract]
    public class ConnectionSetting
    {
        [DataMember]
        public IPAddress ip;
        [DataMember]
        public int remotePort;
        [DataMember]
        public int localPort;

        public ConnectionSetting(IPAddress newIp, int newRemotePort, int newLocalPort)
        {
            ip = newIp;
            remotePort = newRemotePort;
            localPort = newLocalPort;
        }
    }
}