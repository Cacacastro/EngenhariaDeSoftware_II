function MostraEmprestimo(apagou=false)
{   
    if(!apagou)
        event.preventDefault();
    var filtro=document.getElementById("filtro").value; // verifica o filtro
    const URL_TO_FETCH='TelaEmprestimo?acao=consultar&filtro='+filtro;
    fetch(URL_TO_FETCH,{contentType:"text/html;charset=ISO-8859-1"},{method:'get'/*opcional*/}).then(function(response)
    {
        response.text().then(function(result)  //response é um promisse
        {
            // result contém a resposta do módulo dinâmico
            document.getElementById('preview').innerHTML = result;
        });
    }).catch (function(err) {console.error(err);});

}


function ApagaAlteraEmprestimo(acao, cod)
{   event.preventDefault(); // evita refresh da tela
    var url = "";
    switch (acao)
    {
        case "apagar":
            url = "TelaEmprestimo?acao=apagar&cod=" + cod;
            break;
        case "alterar":
            url = "TelaEmprestimo?acao=alterar&cod=" + cod;
            break;
    }
    
    fetch(url,{method:'get'/*opcional*/}).then(function(response)
    {
        response.text().then(function(result)  //response é um promisse
        {
            if (acao === 'apagar')
            {
                document.getElementById("erromsg").innerHTML = result;
                MostraEmprestimo(true);
            } else
            {
                var aux = result;
                var user = aux.split(",");
                var form = document.forms["dados"];
                form.cod.value = user[0];
                form.usu_cod.value = user[2];
                form.emp_data.value = user[3];
                form.emp_duracao.value = user[1];
                form.emp_valortotal.value = user[4];
            }
        });
    }).catch (function(err) {console.error(err);});

}

function GravaEmprestimo()
{
    event.preventDefault(); // evita refresh da tela

    const URL_TO_FETCH = 'TelaEmprestimo';
    
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
            document.getElementById('fdados').reset();
            MostraEmprestimo(true);
        }
         
    }).catch(function (error) {
        console.error(error);
    });

}
