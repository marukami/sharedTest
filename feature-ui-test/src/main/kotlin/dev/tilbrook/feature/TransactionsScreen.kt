package dev.tilbrook.feature

import androidx.compose.ui.semantics.SemanticsNode
import androidx.compose.ui.test.SemanticsMatcher
import androidx.compose.ui.test.SemanticsNodeInteractionsProvider
import dev.tilbrook.test.core.LazyListItemPosition
import io.github.kakaocup.compose.node.element.ComposeScreen
import io.github.kakaocup.compose.node.element.KNode
import io.github.kakaocup.compose.node.element.lazylist.KLazyListItemNode
import io.github.kakaocup.compose.node.element.lazylist.KLazyListNode

class TransactionsScreen(
    semanticsProvider: SemanticsNodeInteractionsProvider
) : ComposeScreen<TransactionsScreen>(semanticsProvider,
    viewBuilderAction = { hasTestTag("TransactionsScreen") }) {

    val transactions = KLazyListNode(
        semanticsProvider = semanticsProvider,
        viewBuilderAction = { hasTestTag("transactionList") },
        itemTypeBuilder = {
            itemType(::TransactionRowNode)
        },
        positionMatcher = { position ->
            SemanticsMatcher.expectValue(LazyListItemPosition, position)
        },
    )
}

class TransactionRowNode(
    semanticsNode: SemanticsNode,
    semanticsProvider: SemanticsNodeInteractionsProvider,
) : KLazyListItemNode<TransactionRowNode>(semanticsNode, semanticsProvider) {
    val id: KNode = child { hasTestTag("transactionId") }
    val amount: KNode = child { hasTestTag("transactionAmount") }
}

