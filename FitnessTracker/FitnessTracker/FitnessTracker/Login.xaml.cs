using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using Newtonsoft.Json;
using Xamarin.Forms;
using Xamarin.Forms.Xaml;

namespace FitnessTracker
{
    [XamlCompilation(XamlCompilationOptions.Compile)]
    public partial class Login : ContentPage
    {

        public Login()
        {
            InitializeComponent();
            BackgroundImage = "bg.jpg";

           
        }

        async private void OnLoginButtonClicked(object sender, EventArgs e)
        {
            string useremail = "";
            string password = "";

            useremail = userNameEntry.Text;
            password = PasswordEntry.Text;

            Request request = new Request();

            string bodyRequest = "{\"userEmail\":\"" + useremail + "\",\"password\":\"" + password + "\"}";



            var loginResponse = await request.callService("userServices/login", bodyRequest, "POST");
            System.Diagnostics.Debug.WriteLine("OnLoginButtonClicked , "+loginResponse.content );

            if (loginResponse.status == true)
            {
                if(loginResponse.content == "STATUS:-1")
                {
                  validationMsgLb.Text = "Invalid user name/ Password";
                    return;
                }

                else if (loginResponse.content == "STATUS:-3")
                {
                  await DisplayAlert("Error!", "Server Error has occured please try again later!", "Ok");
                  return;
                }


                var userStr = loginResponse.content;
                Player player = new Player();
                player = JsonConvert.DeserializeObject<Player>(userStr);

                if (player.email != null)
                {
                    if (Application.Current.Properties.ContainsKey("user"))
                    {
                        System.Diagnostics.Debug.WriteLine("if (Application.Current.Properties.ContainsKey(user) ");

                        // Application.Current.Properties["user"] = null;
                        Application.Current.Properties["user"] = userStr;


                    }

                    else
                    {
                        System.Diagnostics.Debug.WriteLine("Application.Current.Properties.Add(user, " +  userStr);

                        Application.Current.Properties.Add("user", userStr);

                    }



                    await Navigation.PushAsync(new MainPageCS());
                }

                else validationMsgLb.Text = "Invalid user name/ Password";

            }

            else
            {
                validationMsgLb.Text = "Invalid user name/ Password";
            }




        }
    }
}