	let data;


	fetch('/api/games')
	.then(res => res.ok ? res.json() : new Error(res.status))
	.then(json => {
		data = json.games
        table();
		scoreTable(gameScore(data))
	})
	.catch(error => console.log(error))


    //Login

    function login() {
        let form= new FormData(document.getElementById('form_login'));

        fetch('/api/login', {
          method: 'POST',
          body:form
          })

        .then(function (response) {
          if (response.ok) {
            alert( 'Successful login' );
            console.log("Login exitoso")
          } else {
            throw new Error(response.statusText);
          }

        }).catch(function(error) {
          alert('Login fails ' + error.message);
          console.log("Login fails")
        });
    }


    //Logout
    function logout() {

        fetch('/api/logout', {
          method: 'POST',

          })

    }

    //casos especiales
    function cases() {

    }


//GameScore
        function gameScore(data){

        let aux = []

      	for(let i = 0; i < data.length; i++){

      	    for(let j = 0; j < data[i].gameplayers.length; j++){
      	        let exist = false

      	        for(k = 0; k < aux.length; k++){
      	            if(aux[k].id == data[i].gameplayers[j].player.id_player){
      	                exist = true
      	                aux[k].total += data[i].gameplayers[j].score == null ? 0 : data[i].gameplayers[j].score ;
      	                aux[k].won += data[i].gameplayers[j].score == 1.0 ? 1 : 0 ;
                        aux[k].lost += data[i].gameplayers[j].score == 0.0 ? 1 : 0;
                        aux[k].tied += data[i].gameplayers[j].score== 0.5 ? 1 : 0;
      	            }
      	        }

      	        if(!exist){
      	            let obj = {}
                    obj.id = data[i].gameplayers[j].player.id_player;
                    obj.name = data[i].gameplayers[j].player.firstname;

                    obj.total = data[i].gameplayers[j].score == null ? 0 : data[i].gameplayers[j].score ;
                    obj.won = data[i].gameplayers[j].score == 1.0 ? 1 : 0 ;
                    obj.lost = data[i].gameplayers[j].score == 0.0 ? 1 : 0;
                    obj.tied = data[i].gameplayers[j].score== 0.5 ? 1 : 0;

                    aux.push(obj)
      	        }
      	    }
      	}
      	return aux
    }


        function scoreTable(scores){

            let info = document.getElementById('game_score')
            let table = ""
                            table = `<table class="table table-bordered">
                                    <thead class="thead-dark">
                                        <tr>
                                            <th>Name</th>
                                            <th>Total</th>
                                            <th>Won</th>
                                            <th>Lost</th>
                                            <th>Tied</th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                    `

            for(let i = 0; i < scores.length; i++) {
                   table +=  `<tr>
                    <td>${scores[i].name} </td>
                    <td>${scores[i].total} </td>
                    <td>${scores[i].won} </td>
                    <td>${scores[i].lost} </td>
                    <td>${scores[i].tied} </td>
                    </tr>
                    `
            }
        	table += "</tbody> </table>"
            info.innerHTML = table
        }




    	function table(){
    	    console.log(data)

    		let info = document.getElementById('table')
            let table = ""
                            table = `<table class="table table-bordered">

    		                 <thead class="thead-dark">
    		                    <tr>
    		                        <th>Game ID</th>
    								<th>Creation Date</th>
    								<th>Username</th>
    						   </tr>
    						 </thead>
    						 <tbody>`


    		for(let i = 0; i < data.length; i++){
                table +=  `<tr>

    									<td>${data[i].id}</td>
    									<td>${data[i].creationDate}</td>
                                        <td>${data[i].gameplayers.map(e => e.player.username).join(" , ")}</td>
    								</tr>
    								`
    		}
    		table += "</tbody> </table>"
            info.innerHTML = table
    	}

