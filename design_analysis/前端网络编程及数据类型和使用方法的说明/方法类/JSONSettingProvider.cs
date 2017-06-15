using System;
using System.IO;
using System.Runtime.Serialization.Json;

namespace BLL.Setting
{
    public class JsonSettingProvider
    {
        private string settingRoot = Environment.CurrentDirectory + @"\setting.json";
        private ConnectionSetting _currentConnectionSetting;

        public ConnectionSetting ConnectionSetting
        {
            get { return _currentConnectionSetting ?? (_currentConnectionSetting = Load()); }
            set
            {
                _currentConnectionSetting = value;
                Save(_currentConnectionSetting);
            }
        }

        private void Save(ConnectionSetting newConnectionSetting)
        {
            var serializer = new DataContractJsonSerializer(typeof(ConnectionSetting));

            using (FileStream fs = new FileStream(settingRoot, FileMode.OpenOrCreate))
            {
                serializer.WriteObject(fs,newConnectionSetting);
            }
        }

        private ConnectionSetting Load()
        {
            var serializer = new DataContractJsonSerializer(typeof(ConnectionSetting));
            try
            {
                using (FileStream fs = new FileStream(settingRoot, FileMode.Open))
                {
                    var result = (ConnectionSetting)serializer.ReadObject(fs);
                    return result;
                }
            }
            catch (Exception)
            {
                return null;
            }
        }
    }
}