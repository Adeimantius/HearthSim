package com.hearthsim.card.spellcard.concrete;

import com.hearthsim.card.minion.Minion;
import com.hearthsim.card.spellcard.SpellAoeInterface;
import com.hearthsim.card.spellcard.SpellDamage;
import com.hearthsim.card.spellcard.SpellDamageAoe;
import com.hearthsim.event.MinionFilter;
import com.hearthsim.event.MinionFilterTargetedSpell;
import com.hearthsim.exception.HSException;
import com.hearthsim.model.PlayerModel;
import com.hearthsim.model.PlayerSide;
import com.hearthsim.util.tree.HearthTreeNode;

public class HolyNova extends SpellDamage implements SpellAoeInterface {

    private static final byte DAMAGE_AMOUNT = 2;

    /**
     * Constructor
     *
     * @param hasBeenUsed Whether the card has already been used or not
     */
    @Deprecated
    public HolyNova(boolean hasBeenUsed) {
        this();
        this.hasBeenUsed = hasBeenUsed;
    }

    /**
     * Constructor
     * Defaults to hasBeenUsed = false
     */
    public HolyNova() {
        super();
        this.minionFilter = MinionFilterTargetedSpell.OPPONENT;
    }

    @Override
    public MinionFilter getHitsFilter() {
        return MinionFilter.ALL_ENEMIES;
    }

    /**
     * Use the card on the given target
     * Deal 2 damage to all enemy characters and heal all friendly characters by 2
     *
     * @param side
     * @param boardState The BoardState before this card has performed its action. It will be manipulated and returned.
     * @return The boardState is manipulated and returned
     */
    @Override
    protected HearthTreeNode use_core(PlayerSide side, Minion targetMinion, HearthTreeNode boardState, boolean singleRealizationOnly) throws HSException {

        HearthTreeNode toRet = super.use_core(side, targetMinion, boardState, singleRealizationOnly);

        if (toRet != null) {
            PlayerModel currentPlayer = toRet.data_.modelForSide(PlayerSide.CURRENT_PLAYER);

            toRet = currentPlayer.getHero().takeHealAndNotify((byte) 2, PlayerSide.CURRENT_PLAYER, toRet);
            for (Minion minion : currentPlayer.getMinions()) {
                toRet = minion.takeHealAndNotify((byte) 2, PlayerSide.CURRENT_PLAYER, toRet);
            }
        }
        return toRet;
    }
}
