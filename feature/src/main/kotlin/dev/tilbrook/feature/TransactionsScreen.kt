package dev.tilbrook.feature

import androidx.compose.foundation.layout.Arrangement.spacedBy
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ShoppingCart
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ProvideTextStyle
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import dev.tilbrook.test.core.lazyListItemPosition
import kotlin.random.Random
import kotlin.random.asJavaRandom
import kotlin.random.asKotlinRandom

@Immutable
class Transaction(
    val index: Int,
    val units: Long,
)

@Composable
fun TransactionsScreen(
    modifier: Modifier = Modifier,
) {

    val transactions = remember {
        (0..100).map { Transaction(it, it + 100L) }
    }

    Scaffold(modifier = modifier.testTag("TransactionsScreen")) {
        TransactionsScreenContent(
            transactions,
            modifier = Modifier.padding(it),
        )
    }

}

@Composable
fun TransactionsScreenContent(
    transactions: List<Transaction>,
    modifier: Modifier = Modifier,
) {
    LazyColumn(
        modifier = modifier
            .fillMaxWidth()
            .testTag("transactionList")
    ) {
        items(transactions.count()) {
            TransactionRow(
                transaction = transactions[it],
                modifier = modifier.lazyListItemPosition(it)
            )
        }
    }
}

@Composable
fun TransactionRow(
    modifier: Modifier = Modifier,
    transaction: Transaction
) {
    Column(modifier = modifier.padding(horizontal = 24.dp)) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = spacedBy(16.dp),
            modifier = Modifier.padding(8.dp)
        ) {
            ProvideTextStyle(value = MaterialTheme.typography.displaySmall) {
                Icon(
                    Icons.Outlined.ShoppingCart,
                    contentDescription = null,
                    modifier = Modifier.size(64.dp)
                )
                Column {
                    Text(
                        text = "id = ${transaction.index}",
                        modifier = Modifier.testTag("transactionId")
                    )
                    Text(
                        text = "¥ ${transaction.units}",
                        modifier = Modifier.testTag("transactionAmount")
                    )
                }
            }
        }
        HorizontalDivider()
    }
}

@Preview
@Composable
fun PreviewTransactionsScreen() {
    MaterialTheme {
        Scaffold { paddingValues ->
            TransactionsScreen(modifier = Modifier.padding(paddingValues))
        }
    }

}