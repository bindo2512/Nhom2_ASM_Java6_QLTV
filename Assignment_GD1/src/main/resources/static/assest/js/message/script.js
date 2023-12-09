app.controller("chat-ctrl", function($http, $scope) {

this.client = StreamChat.getInstance("wgft9kxdbtzt");
this.client.connectUser(
    {
        id: 'jlahey',
        name: 'Jim Lahey',
        image: 'https://i.imgur.com/fR9Jz14.png',
    },
    "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1c2VyX2lkIjoicmVkLWR1c3QtOSJ9.2dRli43dWGfwyKTeOKew_OCelzsUiftv71QuFSOSx9Q",
);

})