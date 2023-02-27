    let data;
	let params = (new URL(document.location)).searchParams;
    let gameplayer = params.get('gp');
    let opponent;

	fetch(`/api/game_view/${gameplayer}`)
    .then(res => res.ok ? res.json() : new Error(res.status))
    .then(json => {
        data = json

        //look for the opponent and save all info_player into a variable called opponent
         data.gamePlayer.forEach(gp => {
            if(gp.id_gamePlayer != gameplayer){
                opponent = gp.player
            }
         });

        //Ships
        data.ship.forEach(e=>{

            function orientation(ship){
                (ship.shipLocation[0][0] == ship.shipLocation[1][0]) ?
                    orientation = "horizontal"
                    :
                    orientation = "vertical"

                 return orientation
            }
            createShips(e.type, e.shipLocation.length, orientation(e),document.getElementById('ships'+ e.shipLocation[0]),true)
        });

        //Salvoes: identify player and opponent. Show player's ships / opponent hits and shots fired at the opponent plus salvoes' turn
        data.salvo.forEach(salvo=> {

            salvo.salvoLocation.forEach(e=>{

            if(opponent.id_player != salvo.id_player){
                 document.getElementById("salvo" + e).style.background="red"
                  document.getElementById("salvo" + e).innerHTML += salvo.turn;
            }else{
                document.getElementById("ships" + e).style.background="red"
                 document.getElementById("ships" + e).innerHTML += salvo.turn;
            }

            });

        });

        gameInfo();
        gameScore();


    })
	.catch(error => console.log(error))

    //GameInfo
    function gameInfo(){
           		let info = document.getElementById('game_info')
                let aux = ""

           		aux = `<table class="table table-bordered bg-secondary text-center text-light">
           		        <thead class="thead-dark">
                            <tr>
                                <th>Game</th>
                                <th>Player 1</th>
                                <th>Player 2</th>
                             </tr>
                        </thead>

                         <tr>
                         <td>${data.gameId}</td>
                         `

                        for(let i = 0; i < data.gamePlayer.length; i++){
                            aux += `<td>${data.gamePlayer[i].player.username}</td>`
                        }
           		aux += "</tr></table>"
           		info.innerHTML = aux
    }