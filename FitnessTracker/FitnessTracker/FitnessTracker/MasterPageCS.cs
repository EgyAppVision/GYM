using Newtonsoft.Json;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;

using Xamarin.Forms;

namespace FitnessTracker
{
    public class MasterPageCS : ContentPage
    {
       
            public ListView ListView { get { return listView; } }

            ListView listView;
            Player user;

            public MasterPageCS()
            {

            user = new Player();
            string userStr = Application.Current.Properties["user"] as string;
            user = JsonConvert.DeserializeObject<Player>(userStr);


            this.BackgroundImage = "bg.jpg";

                var masterPageItems = new List<MasterPageItem>();
            masterPageItems.Add(new MasterPageItem
            {
                Title = "Home",
                IconSource = "home32.png",
                TargetType = typeof(WallPage)
            });
            masterPageItems.Add(new MasterPageItem
                {
                    Title = "My Info",
                    IconSource = "contacts.png",
                    TargetType = typeof(ProfilePage)
                });

            if (user.type == 1)
            {
                masterPageItems.Add(new MasterPageItem
                {
                    Title = "Find Friends",
                    IconSource = "searchIcon.png",
                    TargetType = typeof(FollowPage)
                });
                masterPageItems.Add(new MasterPageItem
                {
                    Title = "Find a Trainer",
                    IconSource = "people.png",
                    TargetType = typeof(PlayerRequestSession)
                });

                masterPageItems.Add(new MasterPageItem
                {
                    Title = "Manage your sessions",
                    IconSource = "listIcon.png",
                    TargetType = typeof(PlayerSessionList)
                });


            }

            //for tariner
            if (user.type == 2 )
            {
                masterPageItems.Add(new MasterPageItem
                {
                    Title = "Pending Requests",
                    IconSource = "friendRequest.png",
                    TargetType = typeof(Trainer.TrainerSessionsRequests)
                });


                masterPageItems.Add(new MasterPageItem
                {
                    Title = "My running sessions",
                    IconSource = "gymIcon.png",
                    TargetType = typeof(Trainer.TrainerCurrentSessions)
                });

            }


            masterPageItems.Add(new MasterPageItem
            {
                Title = "LogOut",
                IconSource = "logout.png",
                TargetType = typeof(LogOutCS)
            });


            listView = new ListView
            {
                    BackgroundColor = Device.RuntimePlatform == Device.Android ? Color.Silver : Color.GhostWhite,
                    ItemsSource = masterPageItems,
                    ItemTemplate = new DataTemplate(() =>
                    {
                        var grid = new Grid { Padding = new Thickness(5, 10) };
                        grid.ColumnDefinitions.Add(new ColumnDefinition { Width = new GridLength(30) });
                        grid.ColumnDefinitions.Add(new ColumnDefinition { Width = GridLength.Star });
                        var image = new Image();
                        image.SetBinding(Image.SourceProperty, "IconSource");
                        var label = new Label { VerticalOptions = LayoutOptions.FillAndExpand };
                        label.SetBinding(Label.TextProperty, "Title");

                        grid.Children.Add(image);
                        grid.Children.Add(label, 1, 0);

                        return new ViewCell { View = grid };
                    }),
                    SeparatorVisibility = SeparatorVisibility.None
                };





                Icon = "hamburger.png";
                Title = "Fitness Tracker";
                Padding = new Thickness(0, 40, 0, 0);
            //  BackgroundColor = Color.FromHex("#47C293");
           // BackgroundImage = "bg.jpg";
            BackgroundColor = Device.RuntimePlatform == Device.Android ? Color.FromHex("47C293") : Color.FromHex("#47C293");

            StackLayout upperStack = new StackLayout();
           // upperStack.BackgroundColor = Color.FromHex("#47C293");

            Image profileImg = new Image()
            {
                WidthRequest=100, HeightRequest = 100 , Margin = new Thickness(0,10,0,0)
                , HorizontalOptions= LayoutOptions.Center , Source= "profileicon64.png"
            };



         
            Label nameLb = new Label()
            {
                TextColor = Color.White, Text= user.firstName + " " + user.lastName,
                HorizontalOptions = LayoutOptions.Center
            };

            BoxView boxview = new BoxView()
            {//BackgroundColor = Color.FromHex("#47C293") ,HeightRequest = 40 
            };

            upperStack.Children.Add(profileImg);
            upperStack.Children.Add(nameLb);

            upperStack.Children.Add(boxview);



            Content = new StackLayout
            { //BackgroundColor = Device.RuntimePlatform == Device.Android ? Color.Silver : Color.GhostWhite,

            Children = {upperStack, listView }
                };
            }
        }
    }