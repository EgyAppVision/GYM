using Newtonsoft.Json;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

using Xamarin.Forms;
using Xamarin.Forms.Xaml;

namespace FitnessTracker
{
    [XamlCompilation(XamlCompilationOptions.Compile)]
    public partial class RequestTrainerForm : ContentPage
    {
        Trainer.Trainer trainer;
        public RequestTrainerForm( Trainer.Trainer trainerObj)
        {
            trainer = new Trainer.Trainer();
            trainer = trainerObj;
            this.Title = "Request Form for " + trainerObj.name;


            InitializeComponent();
            for(int i = 1; i <= 50; i++)
            {
                sessionnymberPicker.Items.Add(i.ToString());
            }

            sessionnymberPicker.SelectedIndex = 0;
        }

        private void CancelBtn_Clicked(object sender, EventArgs e)
        {
            Navigation.PopAsync();
        }

        private async void ConfirmBtn_Clicked(object sender, EventArgs e)
        {
            User player = new User();
            string userStr = Application.Current.Properties["user"] as string; // from device memory
            player = JsonConvert.DeserializeObject<User>(userStr);


            string body = "{\"userId\":" + player.userId + ",\"trainerId\":" + trainer +
                            ",\"activity\":" + sessionnymberPicker.SelectedItem + ",\"userComment\":" + playerNoteTxt.Text + "}";


            Request wsRequest = new Request();
            var response = await wsRequest.callService("/userServices/addrequesttrainer", "", "POST");


            //handle response
        }
    }
}