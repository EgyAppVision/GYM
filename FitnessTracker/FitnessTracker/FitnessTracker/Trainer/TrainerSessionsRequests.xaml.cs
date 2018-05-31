using Newtonsoft.Json;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

using Xamarin.Forms;
using Xamarin.Forms.Xaml;

namespace FitnessTracker.Trainer
{
    [XamlCompilation(XamlCompilationOptions.Compile)]
    public partial class TrainerSessionsRequests : ContentPage
    {
        List<SessionsRequest> pendingRequests;
        Player trainer;
        public TrainerSessionsRequests()
        {
            InitializeComponent();

            trainer = new Player();
            string userStr = Application.Current.Properties["user"] as string;
            trainer = JsonConvert.DeserializeObject<Player>(userStr);
            Request request = new Request();

            //call get trainer pending requests service

            pendingRequests = new List<SessionsRequest>();

            //to be deleted 
            SessionsRequest req1 = new SessionsRequest();
            req1.id = 1;
            req1.userName = "Wagdy Negm";
            req1.userId = 40;
            req1.userNote = "Please i need sessions ";
            req1.numberOfSessions = 20;

            SessionsRequest req2 = new SessionsRequest();
            req2.id = 2;
            req2.userName = "Heba Elatfy";
            req2.userId = 36;
            req2.userNote = "hiiii ";
            req2.numberOfSessions = 12;

            SessionsRequest req3 = new SessionsRequest();
            req3.id = 3;
            req3.userName = "Ashraf Ibrahim";
            req3.userId = 17;
            req3.userNote = "asap ";
            req3.numberOfSessions = 10;

            pendingRequests.Add(req1);
            pendingRequests.Add(req2);
            pendingRequests.Add(req3);

            lv.ItemsSource = pendingRequests;
            
        }

        private void requestBtn_Clicked(object sender, EventArgs e)
        {
            Button btn = sender as Button;
            var selected = pendingRequests.Find(b => b.id == Convert.ToInt32(btn.ClassId));
            Navigation.PushAsync(new RequestForm(selected));
        }
    }
}