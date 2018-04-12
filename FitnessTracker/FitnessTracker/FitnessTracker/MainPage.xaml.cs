using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using Xamarin.Forms;

namespace FitnessTracker
{
    public partial class MainPage : ContentPage
    {
        public MainPage()
        {
           
            InitializeComponent();
            NavigationPage.SetHasNavigationBar(this, false);
            LogoImg.Source = Device.RuntimePlatform == Device.Android ? ImageSource.FromFile("FitnessLogo.png") : ImageSource.FromFile("FitnessLogo.png");

            if (Application.Current.Properties.ContainsKey("user"))
            {
                if (Application.Current.Properties["user"] != null)
                {
                     Navigation.PushAsync(new MainPageCS());

                }

            }

          


        }



        async private void GotoLogin_Click(object sender, EventArgs e)
        {
            await Navigation.PushAsync(new Login());

        }

        async private void GotoSignUp_click(object sender, EventArgs e)
        {
          //await  DisplayActionSheet("Post", "Cancel" , "Delete" , "Edit", "Share");
        
           await Navigation.PushAsync(new SignUp());

        }

        protected override bool OnBackButtonPressed()
        {
            return true;
        }
    }
}