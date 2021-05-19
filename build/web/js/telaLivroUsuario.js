function MostraLivro(apagou=false)
{   
    if(!apagou)
        event.preventDefault();
    var filtro=document.getElementById("filtro").value; // verifica o filtro
    const URL_TO_FETCH='TelaLivroUser?acao=consultar&filtro='+filtro;
    fetch(URL_TO_FETCH,{contentType:"text/html;charset=ISO-8859-1"},{method:'get'/*opcional*/}).then(function(response)
    {
        response.text().then(function(result)  //response é um promisse
        {
            // result contém a resposta do módulo dinâmico
            document.getElementById('preview').innerHTML = result;
        });
    }).catch (function(err) {console.error(err);});

}
