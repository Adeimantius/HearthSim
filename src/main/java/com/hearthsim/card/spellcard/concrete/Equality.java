package com.hearthsim.card.spellcard.concrete;

import com.hearthsim.event.effect.CardEffectAoeInterface;
import com.hearthsim.card.spellcard.SpellCard;
import com.hearthsim.event.MinionFilter;
import com.hearthsim.event.effect.CardEffectCharacter;
import com.hearthsim.event.MinionFilterTargetedSpell;
import com.hearthsim.event.effect.CardEffectCharacterBuff;

public class Equality extends SpellCard implements CardEffectAoeInterface {

    /**
     * Constructor
     *
     * @param hasBeenUsed Whether the card has already been used or not
     */
    @Deprecated
    public Equality(boolean hasBeenUsed) {
        this();
        this.hasBeenUsed = hasBeenUsed;
    }

    /**
     * Constructor
     *
     * Defaults to hasBeenUsed = false
     */
    public Equality() {
        super();

        this.minionFilter = MinionFilterTargetedSpell.SELF;
    }

    /**
     *
     * Change the Health of ALL minions to 1
     *
     * @param side
     * @param boardState The BoardState before this card has performed its action.  It will be manipulated and returned.
     *
     * @return The boardState is manipulated and returned
     */
    @Override
    protected CardEffectCharacter getEffect() {
        return new CardEffectCharacterBuff(0, 1);
    }

    @Override
    public CardEffectCharacter getAoeEffect() { return this.getEffect(); }

    @Override
    public MinionFilter getAoeFilter() {
        return MinionFilter.ALL_MINIONS;
    }
}
