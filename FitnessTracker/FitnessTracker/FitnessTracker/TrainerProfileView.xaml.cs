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
    public partial class TrainerProfileView : ContentPage
    {
        Trainer.Trainer user;
        List<UserPrefaredActivity> activitiesList;
        List<UserPrefaredPlace> placesList;

        public TrainerProfileView( Trainer.Trainer trainer)
        {
            user = new Trainer.Trainer();
            user =  trainer;
            InitializeComponent();
            this.Title = user.name ;
            activitiesList = new List<UserPrefaredActivity>();
            placesList = new List<UserPrefaredPlace>();
            profileImg.Source = "profileicon64.png";

            //GetSignUpData();
            GetSignUpDataFromLocalStorage();

            firstNameLb.Text = user.name;
            emailLb.Text = user.email;
            mobileNoLb.Text = user.phone.ToString();
            preferedActivityLb.Text = activitiesList.Count() != 0 ? activitiesList.Find(a => a.id == user.activity).value : "";
            preferedPlaceLb.Text = placesList.Count != 0 ? placesList.Find(p => p.id == user.place).value : "";


        }

        public async void GetSignUpDataFromLocalStorage()
        {
            if (Application.Current.Properties.ContainsKey("signUpData") && Application.Current.Properties["signUpData"] != null)
            {
                RootObject signupDataObj = Application.Current.Properties["signUpData"] as RootObject;

                //   RootObject Data = JsonConvert.DeserializeObject<RootObject>(signupDataStr);
                activitiesList = signupDataObj.userPrefaredActivity;
                placesList = signupDataObj.userPrefaredPlace;

            }

            else
            {
                GetSignUpData();
            }



        }
    
    async void GetSignUpData()
    {
        string address = "loadDataServices/getSingupData";
        Request request = new Request();
        var response = await request.callService(address, "", "GET");
        if (response.status == true)
        {
            RootObject DataFresh = JsonConvert.DeserializeObject<RootObject>(response.content);

            if (Application.Current.Properties.ContainsKey("signUpData"))
            {
                Application.Current.Properties["signUpData"] = DataFresh;

            }

            else
            {
                Application.Current.Properties.Add("signUpData", DataFresh);
            }

            activitiesList = DataFresh.userPrefaredActivity;
            placesList = DataFresh.userPrefaredPlace;

           // preferedActivityLb.Text = activitiesList.Count() != 0 ? activitiesList.Find(a => a.id == user.preferedActivity).value : "";
           // preferedPlaceLb.Text = placesList.Count != 0 ? placesList.Find(p => p.id == user.preferedPlace).value : "";

        }
        else
        {
            await DisplayAlert("Error", "Network Error", "OK");
            // await Navigation.PushAsync(new MainPage());
        }

    }


    private async void requestBtn_Clicked(object sender, EventArgs e)
        {
            await Navigation.PushAsync(new RequestTrainerForm(user));
        }
    }
}