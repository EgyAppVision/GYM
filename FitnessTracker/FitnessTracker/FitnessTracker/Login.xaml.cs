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
            if (loginResponse.status == true)
            {
                var userStr = loginResponse.content;
                Player player = new Player();
                player = JsonConvert.DeserializeObject<Player>(userStr);

                if (player.email != null)
                {
                    if (Application.Current.Properties.ContainsKey("user"))
                    {
                        Application.Current.Properties["user"] = null;
                        Application.Current.Properties["user"] = userStr;


                    }

                    else
                    {
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



          //  await Navigation.PushAsync(new MainPageCS());   //to be deleted

        }
    }
}