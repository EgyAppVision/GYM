using Newtonsoft.Json;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

using Xamarin.Forms;
using Xamarin.Forms.Xaml;
using static FitnessTracker.InitialData;
using FitnessTracker.Trainer;
namespace FitnessTracker
{
    [XamlCompilation(XamlCompilationOptions.Compile)]
    public partial class PlayerRequestSession : ContentPage
    {
        List<UserPrefaredPlace> placesList;
        List<UserPrefaredActivity> activitiesList;
        List<Trainer.Trainer> resultList;
        Player currentUser;
        public PlayerRequestSession()
        {
           
            InitializeComponent();
            GetSignUpData();
            resultList = new List<Trainer.Trainer>();
            //currentUser = new Player();
            //string userStr = Application.Current.Properties["user"] as string; // from device memory
            //currentUser = JsonConvert.DeserializeObject<Player>(userStr);

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
                string place = PlacePicker.SelectedItem.ToString();
                string activity = ActivityPicker.SelectedItem.ToString();

                int placeId = place == "Any" ? 0 : placesList.Find(b => b.value == place).id;
                int activityId = activity == "Any" ? 0 : activitiesList.Find(a => a.value == activity).id;

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
                await DisplayAlert("Error! ", "Please Insert Name ", "Ok");
            }
        }




        async void ShowResult()
        {
            foreach (var child in listGrid.Children.ToList())
            {
                listGrid.Children.Remove(child);
            }
            Trainer.Trainer result = new Trainer.Trainer();
            result.name = "Aly Mazhar";
            result.activity = "All";
            result.phone = "01234567899";
            result.place = "Balance Gym";
            resultList.Add(result);

            Trainer.Trainer result2 = new Trainer.Trainer();
            result2.name = "Radwa Alaa";
            result2.activity = "Zomba";
            result2.phone = "01234567899";
            result2.place = "Gold's Gym";
            resultList.Add(result2);
 
            Trainer.Trainer result3 = new Trainer.Trainer();
            result3.name = "Ramadan Sobhy";
            result3.activity = "All";
            result3.phone = "01234567899";
            result3.place = "Balance Gym";

            resultList.Add(result3);

            int place = PlacePicker.SelectedIndex == 0 ? 0 : placesList.Find(p => p.value == PlacePicker.SelectedItem.ToString()).id;
            int activity = ActivityPicker.SelectedIndex == 0 ? 0 : activitiesList.Find(a => a.value == ActivityPicker.SelectedItem.ToString()).id;
            listGrid.RowSpacing = 8.0;

            //http://ServerIP:ServerPort/gymAppMS/userServices/GetAllUsersByname?keyname=ashraf&userId=1&place=1&activity=1

            //Request request = new Request();
            //var response = await request.callService("userServices/GetAllUsersByname?keyname=" + nameEntry.Text + "&userId=" + currentUser.userId + "&place=" + place
            //    + "&activity=" + activity, "", "GET");



            //if (response.status == true)

            //{
            //    var content = response.content;
            //    if (content != "[]")
            //    {
            //        resultList = new List<Trainer.Trainer>();
            //        resultList = JsonConvert.DeserializeObject<List<Trainer.Trainer>>(content);

            for (int i = 0; i < resultList.Count(); i++)
                   {
                       var pl = resultList[i];
                System.Diagnostics.Debug.WriteLine("row is :" + i);
                CreatStack(pl.name, pl.phone, pl.place, pl.activity, i);
                    }
               // }
          //  }

          //  else await DisplayAlert("Network Error!", "Network error has occured", "ok");
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

                foreach (var place in placesList)
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
              //  await Navigation.PushAsync(new WallPage());

            }

        }

        private async void followBtn_Clicked(object sender, EventArgs e)
        {
            var btn = (Button)sender;
            int rowNo = Convert.ToInt32(btn.ClassId);//Grid.GetRow(btn);
            var selected = resultList[rowNo];
           
            // Request followRequest = new Request();
            System.Diagnostics.Debug.WriteLine("followBtn_Clicked , row is :" + rowNo);

            await DisplayAlert("row number", selected.name + " in  " + rowNo, "ok");
            await Navigation.PushAsync(new RequestTrainerForm(selected));

            //var followResponse = await followRequest.callService("userServices/follow?following=" + selected.id + "&follower=" + currentUser.userId, "", "GET");
            //if (followResponse.status == true)
            //{
            //    ShowResult();
            //    //btn.Text = "Unfollow";
            //}

            //else await DisplayAlert("Network Error!", "Network error has occured", "ok");



        }


        void CreatStack(string name, string phone, string place, string activity, int row)
        {
            StackLayout st = new StackLayout();
            st.Orientation = StackOrientation.Vertical;
            st.Margin = new Thickness(0,0,0,20);
          //  st.Padding = new Thickness(10);
            st.BackgroundColor = Color.FromHex("#EEEFF1");


            StackLayout imgStack = new StackLayout();
            imgStack.Orientation = StackOrientation.Horizontal;
            imgStack.HorizontalOptions = LayoutOptions.FillAndExpand;
            imgStack.BackgroundColor = Color.FromHex("#d1d3d6");

            Image userimg = new Image();
            userimg.VerticalOptions = LayoutOptions.Center;
            userimg.HorizontalOptions = LayoutOptions.Start;
            userimg.Margin = new Thickness(3, 3, 3, 3);

            userimg.HeightRequest = 50;
            userimg.WidthRequest = 50;
            userimg.Source = "profileicon32.png";

            Button followbtn = new Button();
            //   followbtn.Text = pl.isFollowing == 1 ? "unfollow" : "follow";
            followbtn.Text = "Request";
            followbtn.VerticalOptions = LayoutOptions.Center;
             followbtn.HorizontalOptions = LayoutOptions.EndAndExpand;
             followbtn.Clicked += followBtn_Clicked;
            followbtn.Margin = new Thickness(60, 0, 0, 0);
            followbtn.ClassId = row.ToString();  

            Label n = new Label();
            Label p = new Label();
            Label a = new Label();
            Label pho = new Label();


            n.Text = name;
            n.FontSize = 15;
            n.FontAttributes = FontAttributes.Bold;
            n.VerticalOptions = LayoutOptions.Center;
            n.VerticalTextAlignment = TextAlignment.Center;
            n.Margin = new Thickness(0, 0, 0, 0);


            var fs = new FormattedString();
            fs.Spans.Add(new Span { Text = "Location : ", FontAttributes = FontAttributes.Bold });
            fs.Spans.Add(new Span { Text = phone});
            p.FormattedText = fs;



            var fs2 = new FormattedString();
            fs2.Spans.Add(new Span { Text = "Activity : ", FontAttributes = FontAttributes.Bold });
            fs2.Spans.Add(new Span { Text = phone });
            a.FormattedText = fs2;


            var fs3 = new FormattedString();
            fs3.Spans.Add(new Span { Text = "Phone : ", FontAttributes = FontAttributes.Bold });
            fs3.Spans.Add(new Span { Text = phone });
            pho.FormattedText = fs3;

       
            pho.TextColor = Color.Blue;


            imgStack.Children.Add(userimg);
            imgStack.Children.Add(n);
            imgStack.Children.Add(followbtn);

            st.Children.Add(imgStack);
            st.Children.Add(p);
            st.Children.Add(a);
            st.Children.Add(pho);

            listGrid.Children.Add(st, 0, row);
            System.Diagnostics.Debug.WriteLine("CreatStack,  row is :" + row);

        }

    }
}