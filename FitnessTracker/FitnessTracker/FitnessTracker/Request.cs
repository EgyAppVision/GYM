using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Net.Http;
using Newtonsoft.Json;
using Newtonsoft.Json.Linq;

namespace FitnessTracker
{
  public  class Request
    {
       public string address { get; set; }
        public string headers { get; set; }
        public string data { get; set; }


        public async Task<Response> callService(string serviceName, string data, string type)
        {
            Response responseObj = new Response();

            try
            {
                HttpClient client = new HttpClient();
                HttpResponseMessage response = new HttpResponseMessage();
                string urlStr1 = "http://66.23.230.93:8080/gymAppMS/" + serviceName;
                switch (type)
                {
                    case "POST":
                        var content = new StringContent(data, Encoding.UTF8, "application/json");
                        //response = await client.GetAsync(serviceName);

                        response = await client.PostAsync(urlStr1, content);
                        break;

                    case "GET":
                        response = await client.GetAsync(urlStr1);
                        break;
                }

                responseObj.statusCode = (int)response.StatusCode;

                if (response.IsSuccessStatusCode)
                {
                    var Content = response.Content.ReadAsStringAsync();
                    JObject jobj = new JObject();


                    responseObj.status = true;
                    responseObj.content = await Content;
                }



                else
                {
                    responseObj.status = false;
                }

                return responseObj;

            }

            catch (Exception ex)
            {
                responseObj.status = false;
                System.Diagnostics.Debug.WriteLine("exception in sending the request , the message is : "+ex.Message + "the inner exception is " + ex.InnerException);
                return responseObj;
            }

            }
            
        


    }
}
