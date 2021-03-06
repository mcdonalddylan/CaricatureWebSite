//booleans which allow for toggling 
let isApply = false;
let isView = false;


async function toggleApply()
{
    const conDiv = document.getElementById("content-area");

    if(isView === true)
    {
        isView = false;

        //remove table from content-area div
        const tableRow = document.getElementById("reim-table-row");
        conDiv.removeChild(tableRow);
    }
    if(isView === false && isApply === false)
    {
        isApply = true;

        const applyForm = document.createElement("form");
        applyForm.action = "reimburse.html";
        applyForm.id = "apply-form";
        applyForm.method = "POST";

        const row1 = document.createElement("div");
        row1.className = "row justify-content-center";

        const selectLabel = document.createElement("label");
        selectLabel.setAttribute("for", "reim-type");
        selectLabel.className = "col-3 label-1";
        selectLabel.textContent = "Select a Reimbursement Type:";
        
        const selectType = document.createElement("select");
        selectType.name = "reim-type";
        selectType.className =  "col-6 reim-select";
        const nullOp = document.createElement("option");
        nullOp.value = "-1";
        nullOp.textContent = "--- None Selected ---";
        const travOp = document.createElement("option");
        travOp.value = "1";
        travOp.textContent = "Travel and Mileage";
        const supplyOp = document.createElement("option");
        supplyOp.value = "2";
        supplyOp.textContent = "Art Supplies";
        const busiOp = document.createElement("option");
        busiOp.value = "3";
        busiOp.textContent = "General Business";
        const medOp = document.createElement("option");
        medOp.value = "4";
        medOp.textContent = "Medical";
        const otherOp = document.createElement("option");
        otherOp.value = "5";
        otherOp.textContent = "Other";

        const row2 = document.createElement("div");
        row2.className = "row justify-content-center";

        const descLabel = document.createElement("label");
        descLabel.setAttribute("for", "reim-desc");
        descLabel.className = "col-3 label-1";
        descLabel.textContent = "Explain what this is covering:";

        const descText = document.createElement("textarea");
        descText.setAttribute("rows", "4");
        descText.setAttribute("cols", "50");
        descText.className = "col-6 entry-desc";
        descText.name = "reim-desc";

        const row3 = document.createElement("div");
        row3.className = "row justify-content-center";

        const amntLabel = document.createElement("label");
        amntLabel.setAttribute("for", "amount");
        amntLabel.className = "col-3 label-1";
        amntLabel.textContent = "Amount you are requesting:";

        const amountText = document.createElement("input");
        amountText.setAttribute("type", "text");
        amountText.className = "col-6 entry";
        amountText.name = "amount";

        const row4 = document.createElement("div");
        row4.className = "row justify-content-center";

        const divBtn = document.createElement("div");
        divBtn.className = "col-12-md text-center";

        const appBtn = document.createElement("input");
        appBtn.setAttribute("type", "submit");
        appBtn.value = "Apply for Reimbursement";
        appBtn.className = "apply-btn";
   
        conDiv.append(applyForm);
        applyForm.append(row1, row2, row3, row4);
        row1.append(selectLabel, selectType);
        selectType.append(nullOp, travOp,supplyOp,busiOp,medOp,otherOp);
        row2.append(descLabel, descText);
        row3.append(amntLabel, amountText);
        row4.append(divBtn);
        divBtn.append(appBtn);
    } 
}

async function toggleView()
{
    const conDiv = document.getElementById("content-area");

    if(isApply === true)
    {
        isApply = false;

        //delete all of the applyBtn elements
        const applyForm = document.getElementById("apply-form");
        conDiv.removeChild(applyForm);
    }
    if(isApply === false && isView === false)
    {
        isView = true;

        const row1 = document.createElement("div");
        row1.className = "row justify-content-center";
        row1.id = "reim-table-row";

        const viewTable = document.createElement("table");
        viewTable.id = "reim-table";
        viewTable.className = "table table-dark";
        const tableHead = document.createElement("thead");
        tableHead.className = "reim-head";

        conDiv.append(row1);
        row1.append(viewTable);
        viewTable.append(tableHead);

        //create header for the table
        const thr = document.createElement("tr");
        const th1 = document.createElement("th");
        th1.textContent = "Amount:";
        const th2 = document.createElement("th");
        th2.textContent = "Status:";
        const th3 = document.createElement("th");
        th3.textContent = "Description:";
        const th4 = document.createElement("th");
        th4.textContent = "Time Submitted:";
        const th5 = document.createElement("th");
        th5.textContent = "Time Resolved:"
        const th6 = document.createElement("th");
        th6.textContent = "Author:"
        const th7 = document.createElement("th");
        th7.textContent = "Resolver:"
        const th8 = document.createElement("th");
        th8.textContent = "Reject:"
        const th9 = document.createElement("th");
        th9.textContent = "Accept:"
        tableHead.append(thr);
        thr.append(th1,th2,th3,th4,th5,th6,th7,th8,th9);

        //get reimbursement data (ajax)
        asyncFetch("http://localhost:8080/Caricature/allReim.json", function(reimbursements)
        {
            const tableBod = document.createElement("tbody");
            viewTable.append(tableBod);
            
            //create the table dynamically
            for (const reim of reimbursements)
            {
                //create all row elements for each reimbursement request
                const tr = document.createElement("tr");
                const amountTd = document.createElement("td");
                amountTd.innerText = reim.amount;
                const statusTd = document.createElement("td");
                statusTd.innerText = reim.status;
                const descTd = document.createElement("td");
                descTd.innerText = reim.description;
                const submitTd = document.createElement("td");
                submitTd.innerText = reim.submitDate;
                const resolvedTd = document.createElement("td");
                resolvedTd.innerText = reim.resolveDate;
                const authorTd = document.createElement("td");
                authorTd.innerText = reim.author.firstName + reim.author.lastName;
                const resolverTd = document.createElement("td");
                resolverTd.innerText = reim.resolver.firstName + reim.resolver.lastName;
                const rejBtnTd = document.createElement("td");
                const rejBtn = document.createElement("button");
                rejBtn.onclick = Rejected(reim.id);
                rejBtn.textContent = "Reject";
                rejBtnTd.append(rejBtn);
                const appBtnTd = document.createElement("td");
                const appBtn = document.createElement("button");
                appBtn.onclick = Approved(reim.id);
                appBtn.textContent = "Approved";
                appBtnTd.append(appBtn);

                //change row color based on whether the reim. request was 
                // accepted or rejected
                if(reim.status == "Denied")
                {
                    tr.style = "background-color: #440000";
                }
                else if(reim.status == "Approved")
                {
                    tr.style = "background-color: #004400";
                }
                tableBod.append(tr);

                console.log("reim status: " + reim.status);
                console.log(reim.status == "Pending");
                //add the approve and reject buttons if pending
                if(reim.status == "Pending")
                {
                    tr.append(amountTd,statusTd,descTd,submitTd,resolvedTd,
                        authorTd,resolverTd,rejBtnTd,appBtnTd);
                }
                //don't add the approve and reject buttons
                else
                {
                    tr.append(amountTd,statusTd,descTd,submitTd,resolvedTd,
                        authorTd,resolverTd);
                }
                
                
            }
        });
        

        
    }
    
    function Approved(reimId)
    {
        console.log(reimId);
        //approve the reimbursement

        //then re-call the toggleView function to refresh 
    }

    function Rejected(reimId)
    {
        console.log(reimId);
        //reject the reimbursement

        //then re-call the toggleView function to refresh 
    }
}