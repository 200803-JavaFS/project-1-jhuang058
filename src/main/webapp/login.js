const url = "http://localhost:8080/project0/";
const table1 = document.getElementsByTagName("table")[0];
const table2 = document.getElementsByTagName("table")[1];
const options = document.getElementById("options-row");

document.getElementById("btn").addEventListener("click", loginFunc);

async function loginFunc() {
    const usern = document.getElementById("username").value;
    const pw = document.getElementById("password").value;

    const user1 = {
        username: usern,
        password: pw
    }

    let resp = await fetch(url + "login", {
        method: "POST",
        body: JSON.stringify(user1),
        credentials: "include"
    })

    if (resp.status === 200) {

        currUser = await resp.json();
        const viewPast_Func = viewPastFunc.bind(currUser.userId);
        const addReimb_Func = addReimbFunc.bind(currUser.userId);
        const findPending_Func = findPendingFunc.bind(currUser.userId);
        const findApproved_Func = findByStatusFunc.bind("approved");
        const findDenied_Func = findByStatusFunc.bind("denied");

        document.getElementsByTagName("h1")[0].innerHTML = "Greetings! " + currUser.firstName + " " + currUser.lastName;
        document.getElementById("login-row").setAttribute("hidden", true);
        table1.removeAttribute("hidden");

        let button3 = document.createElement('button');
        button3.className = "btn btn-success";
        button3.id = "logoutbtn";
        button3.innerText = "Log Out";
        button3.onclick = logoutFunc;
        document.getElementsByTagName("h1")[0].appendChild(button3);

        //manager menu
        if (currUser.role.userRole == 'manager') {

            let button1 = document.createElement('button');
            button1.className = "btn btn-success";
            button1.id = "findAllBtn";
            button1.innerText = "See All Reimbursement";
            button1.onclick = findAllFunc;
            options.appendChild(button1);

            let button2 = document.createElement('button');
            button2.className = "btn btn-success";
            button2.id = "findPendingBtn";
            button2.innerText = "See Pending Requests";
            button2.addEventListener('click', findPending_Func);
            options.appendChild(button2);

            let button4 = document.createElement('button');
            button4.className = "btn btn-success";
            button4.id = "findApprovedBtn";
            button4.innerText = "See Approved Requests";
            button4.addEventListener('click', findApproved_Func);
            options.appendChild(button4);

            let button5 = document.createElement('button');
            button5.className = "btn btn-success";
            button5.id = "findDeniedBtn";
            button5.innerText = "See Denied Requests";
            button5.addEventListener('click', findDenied_Func);
            options.appendChild(button5);
//employee menu
        } else {

            table2.removeAttribute("hidden");
            let button1 = document.createElement('button');
            button1.className = "btn btn-success";
            button1.id = "viewPastBtn";
            button1.innerText = "View Past Tickets";
            button1.onclick = viewPast_Func;
            table1.appendChild(button1);

            let button2 = document.createElement('button');
            button2.className = "btn btn-success";
            button2.id = "addReimbBtn";
            button2.type = "button";
            button2.innerText = "Add Reimbursement Request";
            button2.addEventListener('click', addReimb_Func);
            table2.appendChild(button2);

        }
    } else {
        document.getElementById("login-row").innerText = "Login failed!";
        console.log(resp);
        //document.getElementById("login-row").innerText = "LOGIN FAILED.";
    }
}


async function logoutFunc() {
    let resp = await fetch(url + "logout", {
        credentials: "include"
    })

    if (resp.status === 200) {
        location.reload();
        // console.log("logging out")
        // table1.setAttribute("hidden",true);
        // table2.setAttribute("hidden",true);
        // document.getElementById("login-row").removeAttribute("hidden");
        // document.getElementById("username").value="";
        // document.getElementById("password").value="";
        // document.getElementById("logoutbtn").remove();
        // document.getElementsByTagName("h1")[0].innerHTML = "Welcome to the Revature Reimbursement Portal!";
        // document.getElementById("options-row").innerHTML = "";
        // document.getElementById("result").innerHTML = "";
    }
}

async function findByStatusFunc() {
    document.getElementById("reimbbody").innerText = "";

    let resp = await fetch(url + "reimbursement/"+this, {
        credentials: 'include',
    });

    if (resp.status === 200) {
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
            cell9.innerHTML = reimb.author.firstName + " " + reimb.author.lastName;
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

async function findPendingFunc() {
    let i = 1;
    document.getElementById("reimbbody").innerText = "";

    let resp = await fetch(url + "reimbursement/pending", {
        credentials: 'include',
    });

    if (resp.status === 200) {
        console.log(resp);
        let data = await resp.json();

        for (let reimb of data) {
            i++;
            const approveReqFunc = updateReimbFunc.bind(2, reimb.id, this, i);
            const denyReqFunc = updateReimbFunc.bind(3, reimb.id, this, i);

            let button1 = document.createElement('button');
            button1.className = "btn btn-success";
            button1.id = "approveBtn";
            button1.innerText = "Approve";
            button1.onclick = approveReqFunc;
            let button2 = document.createElement('button');
            button2.className = "btn btn-success";
            button2.id = "denyBtn";
            button2.innerText = "Deny";
            button2.onclick = denyReqFunc;

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
            cell9.innerHTML = reimb.author.firstName + " " + reimb.author.lastName;
            row.appendChild(cell9);
            let cell6 = document.createElement("td");
            //cell6.innerHTML = reimb.resolver;
            row.appendChild(cell6);
            let cell7 = document.createElement("td");
            cell7.id = "status" + i;
            cell7.appendChild(button1);
            cell7.appendChild(button2);
            row.appendChild(cell7);
            let cell8 = document.createElement("td");
            cell8.innerHTML = reimb.type.type;
            row.appendChild(cell8);
            document.getElementById("reimbbody").appendChild(row);

        }
    }
}


async function updateReimbFunc(reimbId, resolverId, i) {
    console.log("in approveReqFunc");

    const reimbRequest = {
        status: this,
        id: reimbId,
        resolver: resolverId
    }
    console.log(reimbRequest);

    let resp = await fetch(url + "update", {
        method: "POST",
        body: JSON.stringify(reimbRequest),
        credentials: "include"
    })

    if (resp.status === 201) {
        document.getElementById("status" + i).innerText = ((this == 2) ? "approved" : "denied");
    } else {
        document.getElementById("result").innerText = "Something went wrong."
    }
}

async function viewPastFunc() {

    document.getElementById("reimbbody").innerText = "";

    let resp = await fetch(url + "reimbursement/" + this, {
        credentials: 'include',
    });

    if (resp.status === 200) {

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
            cell4.innerHTML = new Date(reimb.resolved);
            row.appendChild(cell4);
            let cell5 = document.createElement("td");
            cell5.innerHTML = reimb.description;
            row.appendChild(cell5);
            let cell9 = document.createElement("td");
            cell9.innerHTML = reimb.author.firstName + " " + reimb.author.lastName;
            row.appendChild(cell9);
            let cell6 = document.createElement("td");
            if (reimb.resolver !== null) {
                cell6.innerHTML = reimb.resolver.firstName + " " + reimb.resolver.lastName;
            }
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
        amount: amt,
        description: descrip,
        type: typ,
        author: this
    }

    if (amt > 0) {
        let resp = await fetch(url + "reimbursement", {
            method: "POST",
            body: JSON.stringify(reimbRequest),
            credentials: "include"
        })

        if (resp.status === 201) {
            document.getElementById("result").innerHTML = "Reimbursement Request Submitted!";
            document.getElementById("amount").value = "";
            document.getElementById("description").value = "";
            document.getElementById("type").value = "";
        }
    }
}

async function findAllFunc() {

    document.getElementById("reimbbody").innerText = "";

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
            cell3.innerHTML = new Date(reimb.submitted);
            row.appendChild(cell3);
            let cell4 = document.createElement("td");
            cell4.innerHTML = new Date(reimb.resolved);
            row.appendChild(cell4);
            let cell5 = document.createElement("td");
            cell5.innerHTML = reimb.description;
            row.appendChild(cell5);
            let cell9 = document.createElement("td");
            cell9.innerHTML = reimb.author.firstName + " " + reimb.author.lastName;
            row.appendChild(cell9);
            let cell6 = document.createElement("td");
            if (reimb.resolver !== null) {
                cell6.innerHTML = reimb.resolver.firstName + " " + reimb.resolver.lastName;
            }
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
