<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>

<html>
    
    <head>
        
        <title>Web Application, Project#1</title>
        
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        
        <script type="text/javascript" src="libraries/jquery-3.5.1.min.js"></script>
        <script type="text/javascript" src="libraries/project1.js"></script>        
        
    </head>
    
    <body>
        

        <form name="searchform" id="searchform" onclick="project1.getRates();">
            <fieldset>
                <legend>Currency Conversion Calculator</legend>
                <label for ="rates_d">Exchange Rate Date: </label>
                <input type="text" name="rates_d" id="rates_d">
                <p>Convert to: <select name="currencies" id="currencies">
                        <option value="AFN">AFN</option>
                        <option value="ALL">ALL</option>
                        <option value="ANG">ANG</option>
                        <option value="ARS">ARS</option>
                        <option value="ARS">ARS</option>
                        <option value="ARS">ARS</option>
                        <option value="ARS">ARS</option> 
                        <option value="ARS">ARS</option> 
                    </select> </p>
                <label for="USD">Value (USD ): </label>
                <input type="number" name="USD" id="USD">
                <input type="button" value="Convert" >
             </fieldset>
        </form>

        <div id="resultarea"></div>




        
        
    </body>
</html>