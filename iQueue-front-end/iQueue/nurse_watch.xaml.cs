using System.Windows;

namespace iQueue
{
    /// <summary>
    /// nurse_watch.xaml 的交互逻辑
    /// </summary>
    public partial class nurse_watch : Window
    {
        public nurse_watch()
        {
            InitializeComponent();
        }

        private void adjust_butt_Click(object sender, RoutedEventArgs e)
        {

        }

        private void add_butt_Click(object sender, RoutedEventArgs e)
        {
            add_patient add_window = new add_patient();
            add_window.Show();
        }

        private void remove_butt_Click(object sender, RoutedEventArgs e)
        {

        }
    }
}
