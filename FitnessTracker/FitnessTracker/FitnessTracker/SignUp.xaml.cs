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
    public partial class SignUp : ContentPage
    {
        Player player;
        List<UserGender> userGenderList;
        List<UserPrefaredActivity> userPreferedActivityList;
        List<UserPrefaredPlace> userpreferedPlaceList;

         public  SignUp()
        {




            NavigationPage.SetHasBackButton(this, false);
            userGenderList = new List<UserGender>();
            userPreferedActivityList = new List<UserPrefaredActivity>() ;
            userpreferedPlaceList = new List<UserPrefaredPlace>();


            InitializeComponent();


            GetSignUpData();

            PlacePicker.Items.Add("Select You Prefered Place:");
            PlacePicker.SelectedIndex = 0;



        }




        async void GetSignUpData()
        {
            string address = "loadDataServices/getSingupData";
            Request request = new Request();
            var response = await request.callService(address, "", "GET");
            if(response.status == true )
            {
                RootObject Data = JsonConvert.DeserializeObject<RootObject>(response.content);
                userGenderList = Data.userGender;
                 userPreferedActivityList = Data.userPrefaredActivity;
                 userpreferedPlaceList = Data.userPrefaredPlace;

                foreach (var place in userpreferedPlaceList)
                {
                    PlacePicker.Items.Add(place.value);
                }

                foreach (var genderType in userGenderList)
                {
                    GenderPicker.Items.Add(genderType.value);
                    GenderPicker.SelectedIndex = 0;


                }
            }

            else
            {
               await DisplayAlert("Error", "Network Error", "OK");
                await Navigation.PushAsync(new MainPage());

            }

        }



        async private void Next1_click(object sender, EventArgs e)
        {
            if (!string.IsNullOrWhiteSpace(firstnameEntry.Text) && !string.IsNullOrWhiteSpace(lastnameEntry.Text) && !string.IsNullOrWhiteSpace(passwordEntry.Text) && !string.IsNullOrWhiteSpace(emailEntry.Text) && PlacePicker.SelectedIndex != -1 && GenderPicker.SelectedIndex != -1)
            {
                validationMsgLb.Text = "";
                player = new Player();
                player.firstName = firstnameEntry.Text;
                player.lastName = lastnameEntry.Text;
                player.email = emailEntry.Text;
                player.password = passwordEntry.Text;
                player.gender = userGenderList.Find(g => GenderPicker.SelectedItem.ToString() == g.value).id;
                player.mobile = mobileEntry.Text;
                player.place = userpreferedPlaceList.Find(p => PlacePicker.SelectedItem.ToString() == p.value).id.ToString();
                player.userName = firstnameEntry.Text + lastnameEntry.Text;


                SignUpValidation checkValidationObj = new SignUpValidation();
                checkValidationObj = SignUpValidation.CheckPage1Validation(player);
                System.Diagnostics.Debug.WriteLine("validation : " + player.firstName + " ," + player.lastName + " ," + player.mobile + " ," + player.password + " , " + player.email );
                if (SignUpValidation.isNotValid == true)
                {
                    validationMsgLb.Text = "Please Enter Valid info";
                    //show a validation msg for each field
                }


                else
                {

                    Request requestObj = new Request();
                    var mailExistResponse = await requestObj.callService("userServices/isMailExist?userEmail=" + player.email.ToString(), "", "GET");
                    if (mailExistResponse.status == true && mailExistResponse.content == "true")
                    {
                        emailEntry.Focus();
                        await DisplayAlert("Not Completed!!", "this Email is already used please sign up with another email address", "OK");

                    }

                    else if (mailExistResponse.status == true && mailExistResponse.content == "false")
                    {
                        await Navigation.PushAsync(new SignUp2(player, userPreferedActivityList));

                    }

                    else
                    {
                        await DisplayAlert("Error!", "Network Error, Please try again later", "OK");


                    }


                }
            }


            else
            {
                //await Navigation.PushAsync(new SignUp2(player , userPreferedActivityList)); //to be deleted
                validationMsgLb.Text = "Please Fill all the required fields!";
            }

            }


        }


    }
