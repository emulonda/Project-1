var project1 =  ( function() {

    return {

        init: function() {
            
            $("#version").html( "jQuery Version: " + $().jquery );

        },
        
        getRates: function() {

            if ( $("#search").val() === "" ) {

                alert("You must enter a search parameter!  Please try again.");
                return false;

            }

            $.ajax({ //ajax call to servlet to get which return respsonse if succesful

                url: 'PersonnelServlet',
                method: 'GET',
                data: $('#searchform').serialize(),

                success: function(response) {

                    $("#resultsarea").html(response);

                }

            });

            return false;

        }

    };

}());



