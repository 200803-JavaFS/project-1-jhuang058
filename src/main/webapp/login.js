const url = "http://localhost:8080/project0/";

document.getElementById("btn").addEventListener("click", loginFunc);

async function loginFunc() {
    const usern = document.getElementById("username").value;
    const pw = document.getElementById("password").value;

    const user = {
        username : usern,
        password : pw
    }

    const myHeaders = new Headers();
    myHeaders.append('Access-Control-Allow-Credentials', true);

    let resp = await fetch(url+"login", {
        method: 'POST',
        body: JSON.stringify(user),
        mode: "no-cors",
        credentials: 'include',
        headers: myHeaders
    })

    if (resp.status===200) {
        console.log("success log in")
        
        let data = await resp.json();
        console.log(data);
        
         //document.getElementById("login-row").innerHTML = "Hello "+data.firstName+data.lastName;
        
        // if (data.role.userRole === "manager") {
        //     document.getElementById("login-row").innerHTML = "Manager Console";
        // } else {
        //     document.getElementById("login-row").innerText = "Employee Console";
        // }

        // let options = document.getElementById("table-row");
        // document.getElementById("login-row").innerText = "YOU HAVE LOGGED IN.";
        // let button1 = document.createElement('button');
        // button1.className = "btn btn-success";
        // button1.id = "viewPastTickets";
        // button1.innerText = "View Past Tickets";
        // button1.onclick = viewPastTicsFunc;
        // options.appendChild(button1);
        // let button2 = document.createElement('button');
        // button2.className = "btn btn-success";
        // button2.id = "addReimbReq";
        // button2.innerText = "Add Reimbursement Request";
        // button2.onclick = addReimbReqFunc;
        // options.appendChild(button2);
    } else {
        console.log("log in failed")
        //document.getElementById("login-row").innerText = "LOGIN FAILED.";
    }
}

async function getUserFunc() {
    let resp = await fetch(url+"")
}

async function viewPastTicsFunc() {

}

async function addReimbReqFunc() {

}

// function loginFunc() {
//     const usern = document.getElementById("username").value;
//     const pw = document.getElementById("password").value;

//     const user = {
//         username : username,
//         password : pw
//     }

//     let xhr = new XMLHttpRequest();

//     xhr.onreadystatechange = function () {
//         if(this.readyState == 4 && this.status == 200) {
//             let data = JSON.parse(xhr.responseText);
//             renderHTML(data);
//         }
//     }

//     xhr.open("POST", url+"login");
//     xhr.send(JSON.stringify(user));
// }

// function renderHTML(data) {
//     console.log('Login successful');
// }