using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using Newtonsoft.Json;
using Xamarin.Forms;
using Xamarin.Forms.Xaml;
using static FitnessTracker.SignUp;
using static FitnessTracker.InitialData;

namespace FitnessTracker
{
    [XamlCompilation(XamlCompilationOptions.Compile)]
    public partial class FollowPage : ContentPage
    {
        List<UserPrefaredPlace> placesList;
        List<UserPrefaredActivity> activitiesList;
        List<User> resultList;
        User currentUser;
        public FollowPage()
        {
            this.Title = "Find Friends";
            InitializeComponent();
            placesList = new List<UserPrefaredPlace>();
            activitiesList = new List<UserPrefaredActivity>();
            resultList = new List<User>();
            GetSignUpData();

            currentUser = new User();
            string userStr = Application.Current.Properties["user"] as string; // from device memory
            currentUser = JsonConvert.DeserializeObject<User>(userStr);

            PlacePicker.Items.Add("Any");
            PlacePicker.SelectedIndex = 0;
         

            ActivityPicker.Items.Add("Any");
            ActivityPicker.SelectedIndex = 0;
           
            



        }



       


        private async void SearchButton_clicked(object sender, EventArgs e)
        {
            if (!string.IsNullOrEmpty(nameEntry.Text) || !string.IsNullOrWhiteSpace(nameEntry.Text))
            {
                string name = nameEntry.Text.ToString();
                string place =  PlacePicker.SelectedItem.ToString();
                string activity = ActivityPicker.SelectedItem.ToString();

                int placeId = place == "Any" ? 0: placesList.Find(b=>b.value == place).id ;
                int activityId = activity == "Any" ? 0 :activitiesList.Find(a=>a.value==activity).id;

                Request searchRequest = new Request();
                ShowResult();
            //    var response = await   searchRequest.callService("GetAllUsersByname?name= " + name + "&userId=" + 1 +
            //        "&place=" + placeId + "&activity=" + activityId, null, "GET");

            //    resultList = new List<Player>();
            //    resultList =(List<Player>) JsonConvert.DeserializeObject(response.content);
            //    ShowResult(resultList);
            }

            else
            {
                await DisplayAlert("Error! ", "Please Insert Name , Email or Mobile number ", "Ok");
            }
        }




        async void  ShowResult()
        {
            foreach(var child in listGrid.Children.ToList())
            {
                listGrid.Children.Remove(child);
            }
            //Player result = new Player();
            //result.firstName = "Radwa";
            //result.firstName = "Alaa";

            //result.isFollowing = 1;
            //resultList.Add(result);

            //Player result2 = new Player();
            //result2.firstName = "Radwa2";
            //result2.firstName = "alaa2";

            //result2.isFollowing = 0;
            //resultList.Add(result2);

            //Player result3 = new Player();
            //result3.firstName = "Radwa3";
            //result3.firstName = "alaa3";

            //result3.isFollowing = 1;
            //resultList.Add(result3);
            int place = PlacePicker.SelectedIndex == 0 ? 0 : placesList.Find(p=>p.value == PlacePicker.SelectedItem.ToString()).id;
            int activity = ActivityPicker.SelectedIndex == 0 ? 0 : activitiesList.Find(a => a.value == ActivityPicker.SelectedItem.ToString()).id;

            //http://ServerIP:ServerPort/gymAppMS/userServices/GetAllUsersByname?keyname=ashraf&userId=1&place=1&activity=1

            Request request = new Request();
            var response = await  request.callService("userServices/GetAllUsersByname?keyname=" + nameEntry.Text + "&userId=" + currentUser.userId + "&place=" +place
                +"&activity=" + activity, "", "GET");



            if (response.status == true)

            {
                var content = response.content;
                if (content != "[]")
                {
                    resultList = new List<User>();
                    resultList = JsonConvert.DeserializeObject<List<User>>(content);

                    for (int i = 0; i < resultList.Count(); i++)
                    {
                        var pl = resultList[i];
                        Image userimg = new Image();
                        userimg.VerticalOptions = LayoutOptions.Center;
                        userimg.HorizontalOptions = LayoutOptions.Start;
                        userimg.HeightRequest = 60;
                        userimg.WidthRequest = 60;
                        userimg.Source = "profileicon32.png";



                        Label usernamelb = new Label();
                        usernamelb.Text = pl.firstName + " " + pl.lastName;
                        usernamelb.VerticalOptions = LayoutOptions.Center;
                      //  usernamelb.HorizontalOptions = LayoutOptions.CenterAndExpand;



                        Button followbtn = new Button();
                        followbtn.Text = pl.isFollowing == 1 ? "unfollow" : "follow";
                        followbtn.VerticalOptions = LayoutOptions.Center;
                        followbtn.HorizontalOptions = LayoutOptions.CenterAndExpand;




                        followbtn.Clicked += followBtn_Clicked;

                        listGrid.Children.Add(userimg, 0, i);
                        listGrid.Children.Add(usernamelb, 1, i);
                        listGrid.Children.Add(followbtn, 2, i);


                        //vp = new viewplayer();
                        //vp.username = pl.firstname + " " + pl.lastname;
                        //vp.isfollowed = pl.isfollowing == 1 ? "unfollow" : "follow";
                        //viewlist.add(vp);
                    }
                }
            }

            else await DisplayAlert("Network Error!", "Network error has occured", "ok");
            }
        



        public async void GetSignUpData()
        {
            string address = "loadDataServices/getSingupData";
            Request request = new Request();
            var response = await request.callService(address, "", "GET");
            if (response.status == true)
            {
                RootObject Data = JsonConvert.DeserializeObject<RootObject>(response.content);
                activitiesList = Data.userPrefaredActivity;
                placesList = Data.userPrefaredPlace;

                foreach(var place in placesList)
                {
                    PlacePicker.Items.Add(place.value);
                }



                foreach (var act in activitiesList)
                {
                    ActivityPicker.Items.Add(act.value);
                }
            }

            else
            {
                await DisplayAlert("Error", "Network Error", "OK");
                await Navigation.PushAsync(new WallPage());

            }

        }

        private async void followBtn_Clicked(object sender, EventArgs e)
        {
            var btn = (Button)sender;
            int rowNo =  Grid.GetRow(btn);
           var selected =   resultList[rowNo];

            Request followRequest = new Request();
            var followResponse =    await   followRequest.callService("userServices/follow?following=" + selected.userId + "&follower=" + currentUser.userId , "" , "GET");
           if (followResponse.status == true)
            {
                ShowResult();
                //btn.Text = "Unfollow";
            }

            else await DisplayAlert("Network Error!", "Network error has occured", "ok");



        }
    }

  
}