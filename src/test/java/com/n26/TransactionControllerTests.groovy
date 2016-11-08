package com.n26
import com.n26.controller.TransactionController
import com.n26.model.Transaction
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.http.MediaType
import org.springframework.test.context.junit4.SpringRunner
import org.springframework.test.web.servlet.MockMvc

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status
/**
 * Groovy is used to for testing because it provides easy String manipulation.
 *
 * Here, only happy path is tested but all wrong-paths and exceptions should be tested in real life.
 *
 * @author Biniam Asnake
 */
@RunWith(SpringRunner.class)
@WebMvcTest(TransactionController.class)
public class TransactionControllerTests {

    @Autowired
    private MockMvc mvc

    @Test
    public void shouldCreateTransactionWithoutParentId() throws Exception {

        Transaction transaction1 = new Transaction("car", 1500.00)

        this.mvc.perform(put("/transactions/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(IntegrationTestUtils.asJsonString(transaction1)))
                .andExpect(status().isOk())
                .andExpect(content().json("""
                    {
                        "id":1,
                        "type":"car",
                        "amount":1500.0,
                        "parentId":null
                    }
        """))
    }

    @Test
    public void shouldReturnTransactionById() throws Exception {

        Transaction transaction = new Transaction("house", 20000.00)

        this.mvc.perform(put("/transactions/100")
                .contentType(MediaType.APPLICATION_JSON)
                .content(IntegrationTestUtils.asJsonString(transaction)))
                .andExpect(status().isOk())
                .andExpect(content().json("""
                    {
                        "id":100,
                        "type":"house",
                        "amount":20000.0,
                        "parentId":null
                    }
        """))

        this.mvc.perform(get("/transactions/100")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
    }

    @Test
    public void shouldCreateTransactionWithParentId() throws Exception {

        Transaction transaction1 = new Transaction("car", 1500.00)
        Transaction transaction2 = new Transaction("car", 3000.00, 1)

        this.mvc.perform(put("/transactions/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(IntegrationTestUtils.asJsonString(transaction1)))
                .andExpect(status().isOk())
                .andExpect(content().json("""
                    {
                        "id":1,
                        "type":"car",
                        "amount":1500.0,
                        "parentId":null
                    }
        """))

        this.mvc.perform(put("/transactions/2")
                .contentType(MediaType.APPLICATION_JSON)
                .content(IntegrationTestUtils.asJsonString(transaction2)))
                .andExpect(status().isOk())
                .andExpect(content().json("""
                    {
                        "id":2,
                        "type":"car",
                        "amount":3000.0,
                        "parentId":1
                    }
        """))
    }

    @Test
    public void shouldReturnTransactionsByType() throws Exception {

        this.mvc.perform(get("/types/car")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().string("[1,2]"))
    }

    @Test
    public void shouldReturnTransactionSumById() throws Exception {

        this.mvc.perform(get("/sum/1")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json('{"sum": 4500.00}'))
    }
}