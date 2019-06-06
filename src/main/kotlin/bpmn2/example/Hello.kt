package bpmn2.example

import DAO.AccountDAO
import io.javalin.Javalin
import io.javalin.apibuilder.ApiBuilder.get
import io.javalin.apibuilder.ApiBuilder.post
import model.Operation


fun main(args: Array<String>) {

    val accountDAO = AccountDAO()

    val app = Javalin.create().apply {
        exception(Exception::class.java) { e, ctx -> e.printStackTrace() }
        error(404) { ctx -> ctx.json("not found") }
    }.start(8999)

    app.routes {
        post("/operation") { ctx ->
            val operation = ctx.body<Operation>()
            accountDAO.operation(id = operation.accountId, operation = operation.operation, amount = operation.amount)

        }

        get("/account/:account-id") { ctx ->
            ctx.json(accountDAO.findById(ctx.pathParam("account-id").toInt())!!)
        }



    }


}





