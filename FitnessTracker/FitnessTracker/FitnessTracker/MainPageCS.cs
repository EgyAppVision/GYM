using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;

using Xamarin.Forms;

namespace FitnessTracker
{
    public class MainPageCS : MasterDetailPage
    {
        MasterPageCS masterPage;

        public MainPageCS()
        {
            masterPage = new MasterPageCS();
            Master = masterPage;
            Detail = new NavigationPage(new FitnessTracker.WallPage())
            {
                BarBackgroundColor = Color.FromHex("#47C293"),
                BarTextColor = Color.FromHex("#EEEFF2")
            };
            NavigationPage.SetHasBackButton(this, false);
            NavigationPage.SetHasNavigationBar(this, false);

            masterPage.ListView.ItemSelected += OnItemSelected;

            if (Device.RuntimePlatform == Device.UWP)
            {
                MasterBehavior = MasterBehavior.Popover;
            }
        }

        void OnItemSelected(object sender, SelectedItemChangedEventArgs e)
        {
            var item = e.SelectedItem as MasterPageItem;
            if (item != null)
            {
                Detail = new NavigationPage((Page)Activator.CreateInstance(item.TargetType)

                )
                {
                    BarBackgroundColor = Color.FromHex("#47C293"),
                    BarTextColor = Color.FromHex("#EEEFF2")
                };

                masterPage.ListView.SelectedItem = null;
                IsPresented = false;
            }
        }
    }
}
