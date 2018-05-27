    using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;

using Xamarin.Forms;

namespace FitnessTracker
{
    public class MyInfo : ContentPage
    {
        public MyInfo()
        {
            Title = "My Info";
            Content = new StackLayout
            {
                Children = {
                    new Label {
                        Text = "Player Info  goes here",
                        HorizontalOptions = LayoutOptions.Center,
                        VerticalOptions = LayoutOptions.CenterAndExpand
                    }
                }
            };
        }
    }
}