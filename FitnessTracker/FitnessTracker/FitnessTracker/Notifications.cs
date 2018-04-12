using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;

using Xamarin.Forms;

namespace FitnessTracker
{
    public class Notifications : ContentPage
    {
        public Notifications()
        {
            Title = "Notifications";
            Content = new StackLayout
            {
                Children = {
                    new Label {
                        Text = "Notifications",
                        HorizontalOptions = LayoutOptions.Center,
                        VerticalOptions = LayoutOptions.CenterAndExpand
                    }
                }
            };
        }
    }
}
