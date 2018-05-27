using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;

using Xamarin.Forms;

namespace FitnessTracker
{
    public class LogOutCS : ContentPage
    {
        public LogOutCS()

        {
            ClearData();
            Navigation.PushAsync(new MainPage());
            Title = "LogOut";
            Content = new StackLayout
            {
                Children = {

                }
            };
        }


       async void ClearData()
        {

            Application.Current.Properties["user"] = null;
            await Navigation.PushAsync(new MainPage());



        }
    }
}
