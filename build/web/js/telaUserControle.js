function MostraUsuario(apagou=false)
{   
    if(!apagou)
        event.preventDefault();
    var filtro=document.getElementById("filtro").value; // verifica o filtro
    const URL_TO_FETCH='TelaUsuario?acao=consultar&filtro='+filtro;
    fetch(URL_TO_FETCH,{contentType:"text/html;charset=ISO-8859-1"},{method:'get'/*opcional*/}).then(function(response)
    {
        response.text().then(function(result)  //response é um promisse
        {
            // result contém a resposta do módulo dinâmico
            document.getElementById('preview').innerHTML = result;
        });
    }).catch (function(err) {console.error(err);});

}


function ApagaAlteraUser(acao, cod)
{   //event.preventDefault(); // evita refresh da tela
    var url = "";
    switch (acao)
    {
        case "apagar":
            url = "TelaUsuario?acao=apagar&cod=" + cod;
            break;
        case "alterar":
            url = "TelaUsuario?acao=alterar&cod=" + cod;
            break;
    }
    
    fetch(url,{method:'get'/*opcional*/}).then(function(response)
    {
        response.text().then(function(result)  //response é um promisse
        {
            if (acao === 'apagar')
            {
                document.getElementById("erromsg").innerHTML = result;
                alert("Usuário deletado!");
                MostraUsuario(true);
            } else
            {
                var aux = result;
                var user = aux.split(",");
                var form = document.forms["dados"];
                form.cod.value = user[0];
                form.nome.value = user[1];
                form.fone.value = user[2];
                form.end.value = user[3];
                form.email.value = user[4];
                form.senha.value = user[5];
                form.admin.value = user[6];
            }
        });
    }).catch (function(err) {console.error(err);});

}

function GravaUser()
{
    event.preventDefault(); // evita refresh da tela

    const URL_TO_FETCH = 'TelaUsuario';
    
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
        //alert(retorno);
        if (retorno.startsWith('Erro')) // problemas ao alterar/gravar
        {
            document.getElementById('erromsg').innerHTML = retorno;
            document.getElementById('erro').style.display = "block";
        } else  // tudo OK, limpar o formulário
        {
            document.getElementById('fdados').reset();
            MostraUsuario(true);
        }
         
    }).catch(function (error) {
        console.error(error);
    });
      
}

