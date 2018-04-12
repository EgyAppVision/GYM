using Newtonsoft.Json;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

using Xamarin.Forms;
using Xamarin.Forms.Xaml;
using static FitnessTracker.InitialData;

namespace FitnessTracker
{
    [XamlCompilation(XamlCompilationOptions.Compile)]
    public partial class ProfilePage : ContentPage
    {
        Player user;
        List<UserPrefaredActivity> activitiesList;
        List<UserPrefaredPlace> placesList;
        public ProfilePage()
        {
            InitializeComponent();
            user = new Player();
            string userStr = Application.Current.Properties["user"] as string;
            user = JsonConvert.DeserializeObject<Player>(userStr);

            this.Title = user.firstName + " " + user.lastName;
            activitiesList = new List<UserPrefaredActivity>();
            placesList = new List<UserPrefaredPlace>();
            profileImg.Source = "profileicon64.png";
           
            GetSignUpData();

            firstNameLb.Text = user.firstName;
            lastNameLb.Text = user.lastName;
            emailLb.Text = user.email;
            dopLb.Text = user.birthDate;
            userNameLb.Text = user.userName;
            weightLb .Text= user.weight.ToString() + " Kg";
            heightLb.Text = user.height.ToString() + " cm";
            mobileNoLb.Text = user.mobile.ToString();


        }


        public async void GetSignUpData()
        {
            string address = "loadDataServices/getSingupData";
            Request request = new Request();
            var response = await request.callService(address, "", "GET");
            if (response.status == true)
            {
                RootObject Data = JsonConvert.DeserializeObject<RootObject>(response.content);
                activitiesList = Data.userPrefaredActivity;
                placesList = Data.userPrefaredPlace;
                preferedActivityLb.Text = activitiesList.Count() != 0 ? activitiesList.Find(a => a.id == user.preferedActivity).value : "";
                preferedPlaceLb.Text = placesList.Count != 0 ? placesList.Find(p => p.id == user.preferedPlace).value : "";

            }

            else
            {
                await DisplayAlert("Error", "Network Error", "OK");
              //  await Navigation.PushAsync(new WallPage());

            }

        }
    }
}