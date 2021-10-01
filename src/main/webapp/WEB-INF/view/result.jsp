<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>

    <!DOCTYPE html>
    <html lang=pt>

    <head>
        <title>MA-JU</title>
        <meta charset="UTF-8">
        <link rel="stylesheet" href="style.css">
    </head>

    <body>
        <br />
        <div>
            <img class="resultdraw" src=${foto} width="200" height="200">

            <ul>
                <li>Cidade: ${cidade}</li>
                <li>Estado: ${estado}</li>
                <li>País: ${pais}</li>
                <li>Condição climática: ${condicao}</li>
                <li>Sensação térmica: ${sensacao} C°</li>
                <li>Humidade: ${humidade} %</li>
                <li>Vento: ${vento} Km/Hora</li>
            </ul>
        </div>
    </body>

    </html>