# 

#  APIdoc

---------------------------
When server start it have six tip in memory. 
---------------------------

- ### Get a tip

Server find tip by {id} and return it

GET `/portal/rest/demo/tip/{id}`


Response 200

    {
        "id":"1",
        "text":"Do not interact with people who are not very fit for you."
    }

- ### Get a full list of all tips

Server return a list of all tips

GET `/portal/rest/demo/tip`


Response 200

    [
       {
          "id":"1",
          "text":"Do not interact with people who are not very fit for you."
       },
       {
          "id":"2",
          "text":"Do not be afraid to take risks, you can change everything."
       },
       ...
       {
          "id":"n",
          "text":"Nobody understands what he does: just remember this."
       }
    ]

- ### Add new tip

The server stores a new tip in memory and returns it. If the text of the new tip coincides with the text of other tips, then the tip is returned from the memory

POST `/portal/rest/demo/tip`

             
    DATA:   {
                "text"   : "Some tip thet you add to server",
            }
                
Response 200

    {
        "id":"7",
        "text":"Some tip thet you add to server"
    }

- ### Update existing tip

Server find tip by {id} and update it in memory and return it

PUT `/portal/rest/demo/tip/{id}`

    DATA:   {
                "id":"7",
                "text"   : "Some tip thet you change",
            }


Response 200

    {
        "id":"7",
        "text":"Some tip thet you change"
    }
    
- ### Delete existing tip

Server find tip by {id} and delete it

DELETE `/portal/rest/demo/tip/{id}`

Response 200

    
- ### Get random tip

Server return a random tip

GET `/portal/rest/demo/random-tip`


Response 200

    {
        "id":"1",
        "text":"Do not interact with people who are not very fit for you."
    }
