const url = "http://localhost:8080/project0/";

document.getElementById("btn").addEventListener("click", loginFunc);

async function loginFunc() {
    const usern = document.getElementById("username").value;
    const pw = document.getElementById("password").value;

    const user = {
        username : usern,
        password : pw
    }

    let resp = await fetch(url+"login", {
        method: "POST",
        body: JSON.stringify(user),
        credentials: "include"
    })

    if (resp.status===200) {

        let data = await resp.json();
        let viewPast_Func = viewPastFunc.bind(data.userId);
        let addReimb_Func = addReimbFunc.bind(data.userId);

        document.getElementById("login-row").innerHTML = "Greetings! "+data.firstName+" "+data.lastName;

        if (data.role.userRole == 'manager') {

            let button1 = document.createElement('button');
            button1.className = "btn btn-success";
            button1.id = "findAllBtn";
            button1.innerText = "Find All Reimbursement";
            button1.onclick = findAllFunc;
            document.getElementById("table-row").appendChild(button1);

        } else {

            let button1 = document.createElement('button');
            button1.className = "btn btn-success";
            button1.id = "viewPastBtn";
            button1.innerText = "View Past Tickets";
            button1.onclick = viewPast_Func;
            document.getElementById("table-row").appendChild(button1);

            let button2 = document.createElement('button');
            button2.className = "btn btn-success";
            button2.id = "addReimbBtn";
            button2.innerText = "Add Reimbursement Request";

            
            button2.addEventListener('click', addReimb_Func);
            document.getElementById("table-row").appendChild(button2);

        }
    } else {
        document.getElementById("login-row").innerText = "Login failed!";
        console.log(resp);
        //document.getElementById("login-row").innerText = "LOGIN FAILED.";
    }
}


async function viewPastFunc() {
    console.log("searching past tickets...")
    document.getElementById("reimbbody").innerText ="";

    console.log(this);
    let resp = await fetch(url + "reimbursement/"+this, {
        credentials: 'include',
    });

    if (resp.status === 200) {
        console.log(resp);
        
        let data = await resp.json();
        for (let reimb of data) {
            console.log(reimb); 
            let row = document.createElement("tr");
            let cell = document.createElement("td");
            cell.innerHTML = reimb.id;
            row.appendChild(cell);
            let cell2 = document.createElement("td");
            cell2.innerHTML = reimb.amount;
            row.appendChild(cell2);
            let cell3 = document.createElement("td");
            cell3.innerHTML = new Date(reimb.submitted);
            row.appendChild(cell3);
            let cell4 = document.createElement("td");
            //cell4.innerHTML = reimb.resolved;
            row.appendChild(cell4);
            let cell5 = document.createElement("td");
            cell5.innerHTML = reimb.description;
            row.appendChild(cell5);
            let cell9 = document.createElement("td");
            cell9.innerHTML = reimb.author.firstName +" "+reimb.author.lastName;
            row.appendChild(cell9);
            let cell6 = document.createElement("td");
            //cell6.innerHTML = reimb.resolver;
            row.appendChild(cell6);
            let cell7 = document.createElement("td");
            cell7.innerHTML = reimb.status.status;
            row.appendChild(cell7);
            let cell8 = document.createElement("td");
            cell8.innerHTML = reimb.type.type;
            row.appendChild(cell8);
            document.getElementById("reimbbody").appendChild(row);
        }
    }
}

async function addReimbFunc() {
    console.log("in addReimbFunc")

    let amt = document.getElementById("amount").value;
    let descrip = document.getElementById("description").value;
    let typeString = document.getElementById("type").value;
    let typ;
    switch (typeString) {
        case "Travel":
            typ = 1;
            break;
        case "Certification":
            typ = 2;
            break;
        case "Relocation":
            typ = 3;
            break;
    }


    const reimbRequest = {
        amount : amt,
        description : descrip,
        type : typ,
        author : this
    }

    let resp = await fetch(url+"reimbursement", {
        method: "POST",
        body: JSON.stringify(reimbRequest),
        credentials: "include"
    })

    if (resp.status === 201) {
        document.getElementById("addReimb").innerHTML = "Reimbursement Request Submitted!";

    }

}

async function findAllFunc() {

    document.getElementById("reimbbody").innerText ="";

    let resp = await fetch(url + "reimbursement", {
        credentials: 'include',
    });

    if (resp.status === 200) {
        console.log(resp);
        let data = await resp.json();
        for (let reimb of data) {
            console.log(reimb);
            let row = document.createElement("tr");
            let cell = document.createElement("td");
            cell.innerHTML = reimb.id;
            row.appendChild(cell);
            let cell2 = document.createElement("td");
            cell2.innerHTML = reimb.amount;
            row.appendChild(cell2);
            let cell3 = document.createElement("td");
            cell3.innerHTML = reimb.submitted;
            row.appendChild(cell3);
            let cell4 = document.createElement("td");
            //cell4.innerHTML = reimb.resolved;
            row.appendChild(cell4);
            let cell5 = document.createElement("td");
            cell5.innerHTML = reimb.description;
            row.appendChild(cell5);
            let cell9 = document.createElement("td");
            cell9.innerHTML = reimb.author;
            row.appendChild(cell9);
            let cell6 = document.createElement("td");
            //cell6.innerHTML = reimb.resolver;
            row.appendChild(cell6);
            let cell7 = document.createElement("td");
            cell7.innerHTML = reimb.status;
            row.appendChild(cell7);
            let cell8 = document.createElement("td");
            cell8.innerHTML = reimb.type;
            row.appendChild(cell8);
            document.getElementById("reimbbody").appendChild(row);
        }
    }
}

// async function getUserFunc() {
//     let resp = await fetch(url+"")
// }

// async function viewPastTicsFunc() {

// }

// async function addReimbReqFunc() {

// }

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