<!DOCTYPE HTML>
<html>
<head>
    <title>Jag gjorde saker</title> 
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
    <link rel="stylesheet" href="/css/style.css" />
</head>
<body>

    <div class="container">
        <div class="control-group">
            <h2 class="muted">Add a done</h2>

            <div><i>Logged in as ${username}</i></div>
            <div><i>The date is ${date}</i></div>

            <form name="employee" action="/addDone" method="post">
                <div class="control-group">
                    <label class="control-label" for="consultantDone">Did this</label>                    
                    <textarea class="form-control" rows="3" name="consultantDone" placeholder="I did..." required></textarea>
                </div>
                <div class="controls"> 
                    <input type="submit" value="Done!" class="btn btn-primary"/>
                    <input type="hidden" name="date" value ="${date}"/>               
                </div>
            </form>
        </div>

		<table>
			<#list calendar as week>
				<tr>
					<#list week as dy>
				    	<#if dy.isPartOfMonth()>
							<#assign dayClass = "im">
						<#else>
							<#assign dayClass = "om">
						</#if>
					    <td class="${dayClass}">
					    	<a href="/date/${dy}">${dy.day}</a>
					    </td>
					</#list>
				</tr>
            </#list>
		</table>

        <div>

        <#if dones?size == 0 >
        	No dones yet.
        <#else>
            <#list dones as done>
            <div>
            <form action="/deleteDone/${done.id}" method="POST">
                <input type="submit" value = "X"/>
                <input type="hidden" name="date" value ="${date}"/>
                <textarea readonly class="form-control consultantPrevDone">
                    ${done.consultantDone}
                </textarea>
            </form>
            </div>
            </#list>
        </#if>
        </div>
    </div>

</body>

</html>
