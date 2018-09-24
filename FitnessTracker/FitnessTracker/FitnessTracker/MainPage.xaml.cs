using Newtonsoft.Json;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using Xamarin.Forms;
using static FitnessTracker.InitialData;

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
                System.Diagnostics.Debug.WriteLine("Application.Current.Properties.ContainsKey(\"user\")");
                if (Application.Current.Properties["user"] != null)
                {
                    System.Diagnostics.Debug.WriteLine("Application.Current.Properties[\"user\"] != null");

                    Navigation.PushAsync(new MainPageCS());

                }

            }

          


        }



        async void GetSignUpData()
        {
            string address = "loadDataServices/getSingupData";
            Request request = new Request();
            var response = await request.callService(address, "", "GET");
            if (response.status == true)
            {
                RootObject Data = JsonConvert.DeserializeObject<RootObject>(response.content);

                if (Application.Current.Properties.ContainsKey("signUpData"))
                {
                    Application.Current.Properties["signUpData"] = Data;
                
                }

                else
                {
                    Application.Current.Properties.Add("signUpData",  Data);
                }

            }
            else
            {
                await DisplayAlert("Error", "Network Error", "OK");
               // await Navigation.PushAsync(new MainPage());

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