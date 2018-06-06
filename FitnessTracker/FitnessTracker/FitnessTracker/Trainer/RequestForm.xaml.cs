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
    public partial class RequestForm : ContentPage
    {
        SessionsRequest requestForm;
        public RequestForm(SessionsRequest requesObj)
        {

            InitializeComponent();
            requestForm = new SessionsRequest();

            sessionnymberPicker.SelectedIndex = -1;
            for (int i = 1; i <= 50; i++)
            {
                sessionnymberPicker.Items.Add(i.ToString());
            }

            playerNoteTxt.Text = requesObj.userNote;
            sessionnymberPicker.SelectedItem = requesObj.numberOfSessions.ToString();

            senderNameLb.Text = requesObj.userName; 
            var tapGestureRecognizer = new TapGestureRecognizer();
            tapGestureRecognizer.Tapped += (s, e) => {

                //get player object from id
                User pl = new User();
               


                Navigation.PushAsync(new ProfilePage(pl));
            };
            senderNameLb.GestureRecognizers.Add(tapGestureRecognizer);
        }


        private void RejectBtn_Clicked(object sender, EventArgs e)
        {
            Navigation.PopAsync();
        }

        private void ConfirmBtn_Clicked(object sender, EventArgs e)
        {
            Navigation.PushAsync(new TrainerCreateWorkout(requestForm));
        }
    }
}