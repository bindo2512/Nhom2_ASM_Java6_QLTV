app.controller("chat-ctrl", function($http, $scope) {

    
    // the same can be done with plain javascript

this.client = StreamChat.getInstance("wgft9kxdbtzt");
const token2 = this.client.createToken('john', Math.floor(Date.now() / 1000) + (60 * 60));
this.client.connectUser(
    {
        id: 'jlahey',
        name: 'Jim Lahey',
        image: 'https://i.imgur.com/fR9Jz14.png',
    },
    client.devToken(token2),
);

})