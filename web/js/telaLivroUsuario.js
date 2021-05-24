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

function MostraLivroReserva(apagou=false)
{   
    if(!apagou)
        event.preventDefault();
    var filtro=document.getElementById("filtro").value; // verifica o filtro
    const URL_TO_FETCH='TelaLivroReserva?acao=consultar&filtro='+filtro;
    fetch(URL_TO_FETCH,{contentType:"text/html;charset=ISO-8859-1"},{method:'get'/*opcional*/}).then(function(response)
    {
        response.text().then(function(result)  //response é um promisse
        {
            // result contém a resposta do módulo dinâmico
            document.getElementById('preview').innerHTML = result;
        });
    }).catch (function(err) {console.error(err);});

}
function GeraEmprestimo(cod)
{   
     event.preventDefault(); // evita refresh da tela

    const URL_TO_FETCH = 'TelaEmprestimoUser?cod='+cod; 
    const data = new URLSearchParams();
    for (const pair of new FormData(document.getElementById('fdados'))) {
        data.append(pair[0], pair[1]);
    }
    data.append('acao', 'confirmar');
    fetch(URL_TO_FETCH, { method: 'post', body: data 
    }).then(function (response) {
        return response.text();
    }).then(function (retorno) {
        // result recebe a resposta do módulo dinâmico

        if (retorno.startsWith('Erro')) // problemas ao alterar/gravar
        {
            document.getElementById('erromsg').innerHTML = retorno;
            document.getElementById('erro').style.display = "block";
        } else  // tudo OK, limpar o formulário
        {
            alert(retorno);
        }
         
    }).catch(function (error) {
        console.error(error);
    });
}

function GeraReserva(cod)
{   
     event.preventDefault(); // evita refresh da tela

    const URL_TO_FETCH = 'TelaReservaUser?cod='+cod; 
    const data = new URLSearchParams();
    for (const pair of new FormData(document.getElementById('fdados'))) {
        data.append(pair[0], pair[1]);
    }
    data.append('acao', 'confirmar');
    fetch(URL_TO_FETCH, { method: 'post', body: data 
    }).then(function (response) {
        return response.text();
    }).then(function (retorno) {
        // result recebe a resposta do módulo dinâmico

        if (retorno.startsWith('Erro')) // problemas ao alterar/gravar
        {
            document.getElementById('erromsg').innerHTML = retorno;
            document.getElementById('erro').style.display = "block";
        } else  // tudo OK, limpar o formulário
        {
            alert(retorno);
        }
         
    }).catch(function (error) {
        console.error(error);
    });
}