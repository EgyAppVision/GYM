
using Newtonsoft.Json;
using Newtonsoft.Json.Linq;
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
    public partial class SignUp2 : ContentPage
    {
        Player player_signUp2 ;
        List<UserPrefaredActivity> activityList;
        public SignUp2( Player player , List< UserPrefaredActivity> userActivityList)
        {
            InitializeComponent();
            DateTime dt = new DateTime(01/05/1980);
            DOPPicker.Date = dt;
            player_signUp2 = new Player();
            player_signUp2 = player;
            activityList = new List<UserPrefaredActivity>();
            activityList = userActivityList;


            foreach(var activity in activityList)
            {
                ActivityPicker.Items.Add(activity.value);
            }


        }
         private void Next2_click(object sender, EventArgs e)
        {




            if (!string.IsNullOrWhiteSpace(weightEntry.Text) && !string.IsNullOrWhiteSpace(heightEntry.Text) && !string.IsNullOrWhiteSpace(fatperEntry.Text))
            {
                if (weightEntry.Text.Length <= 1 || weightEntry.Text.Length >= 4 || heightEntry.Text.Length <=2 ||heightEntry.Text.Length >= 4)
                validationMsgLb.Text = "";
                try
                {

                   // dop = dop.Substring(0, dop.Length - 10);
                    player_signUp2.birthDate = DOPPicker.Date.ToString("yyyy-MM-dd");
                    player_signUp2.weight = Convert.ToInt32(weightEntry.Text);
                    player_signUp2.height = Convert.ToInt32(heightEntry.Text);
                    player_signUp2.fatPercentage = Convert.ToDouble(fatperEntry.Text);
                    player_signUp2.activity = activityList.Find(a =>  ActivityPicker.SelectedItem.ToString() == a.value).id;
                    player_signUp2.type = 1;


                    sendSignUpRequest(player_signUp2);

                }
                catch (Exception ex)
                {

                }
            }

            else
            {
                validationMsgLb.Text = "Please Fill all the required fields!";
                
            }
        }



     

      async  void  sendSignUpRequest(Player player_signUp2)
        {

           // var jsonStr = JsonConvert.SerializeObject(player_signUp2);

            string jsonStr = "{\"firstName\":\"" + player_signUp2.firstName + "\",\"lastName\":\"" + player_signUp2.lastName
                + "\",\"gender\":\"" + player_signUp2.gender + "\", \"email\" : \"" + player_signUp2.email + "\",\"preferedActivity\" :\"" + player_signUp2.activity +
                "\",\"preferedPlace\" :\"" + player_signUp2.place + "\",\"birthDate\" :\"" + player_signUp2.birthDate +
                "\",\"weight\":" + player_signUp2.weight + ",\"height\":" + player_signUp2.height + ",\"fatPercentage\":" + player_signUp2.fatPercentage +
                ",\"type\":" + player_signUp2.type + ",\"mobile\":\"" + player_signUp2.mobile + "\",\"userName\":\"" + player_signUp2.firstName + player_signUp2.lastName +
                "\",\"password\":\"" + player_signUp2.password + "\"}";


            string address = "userServices/userSignUp";
            Request request = new Request();
            var  response= await request.callService(address, jsonStr, "POST");
            if (response.status == true)
            {

               
                if (response.content.Contains("STATUS:0"))
                {
                    //   "STATUS:0,UserID:29"
                    string b = response.content.Substring(16, response.content.Length - 16);
                    player_signUp2.userId =Convert.ToInt32(b );

                    await DisplayAlert("Completed", "Regestration to Fitness Tracker has been completed", "OK");
                    var userStrObj = JsonConvert.SerializeObject(player_signUp2);
                    if (Application.Current.Properties.ContainsKey("user"))
                    {
                        Application.Current.Properties["user"] = userStrObj;
                    }

                    else
                    {
                        Application.Current.Properties.Add("user", userStrObj);
                    }


                    Application.Current.Properties["user"] = userStrObj;
                    await Navigation.PushAsync(new MainPageCS());

                }

                else
                {
                    await DisplayAlert("Failed", "Regestration to Fitness Tracker has been Failed!, Please try again ", "OK");

                }
            }
            else
            {
                await DisplayAlert("Failed", "Regestration to Fitness Tracker has been Failed!, Please try again ", "OK");

            }





        }
    }

    public class signUpResponse
    {
        public int STATUS { get; set; }
        public int UserID { get; set; }
    }
}